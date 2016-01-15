package com.util.volley;

/**
 * Created by risen on 16/1/8.
 */

import com.android.volley.VolleyError;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.util.SystemParams;

public abstract class NetworkHelper<T> implements Response.Listener<JSONObject>, ErrorListener {
    private Context context;

    public NetworkHelper(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }

    protected NetworkRequest getRequestForGet(String url, List<NameValuePair> params) {
        if (params == null) {
            return new NetworkRequest(url, this, this);
        } else {
            return new NetworkRequest(url, params, this, this);
        }
    }

    protected NetworkRequest getRequestForPost(String url, Map<String, String> params) {
        return new NetworkRequest(Method.POST, url, params, this, this);
    }

    public void sendGETRequest(String url, List<NameValuePair> params) {
        VolleyQueueController.getInstance().
                getRequestQueue(getContext()).add(getRequestForGet(url, params));
    }

    public void sendPostRequest(String url, Map<String, String> params) {
        VolleyQueueController.getInstance().
                getRequestQueue(context).add(getRequestForPost(url, params));
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("Amuro", error.networkResponse.statusCode + "");
        disposeVolleyError(error);
    }

    protected abstract void disposeVolleyError(VolleyError error);

    @Override
    public void onResponse(JSONObject response) {
        Log.d("Amuro", response.toString());
        disposeResponse(response);
    }

    protected abstract void disposeResponse(JSONObject response);

    private UIDataListener<T> uiDataListener;

    public void setUiDataListener(UIDataListener<T> uiDataListener) {
        this.uiDataListener = uiDataListener;
    }

    protected void notifyDataChanged(T data) {
        if (uiDataListener != null) {
            uiDataListener.onDataChanged(data);
        }
    }

    protected void notifyErrorHappened(String errorCode, String errorMessage) {
        if (uiDataListener != null) {
            uiDataListener.onErrorHappened(errorCode, errorMessage);
        }
    }

}
