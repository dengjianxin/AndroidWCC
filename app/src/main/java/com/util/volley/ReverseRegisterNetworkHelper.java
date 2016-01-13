package com.util.volley;

/**
 * Created by risen on 16/1/8.
 */

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;

import com.android.volley.VolleyError;
import com.maincode.model.HttpBaseModel;
import com.util.SystemParams;
import com.view.DialogActivity;

public class ReverseRegisterNetworkHelper extends NetworkHelper<HttpBaseModel> {

    Context mContext;

    public ReverseRegisterNetworkHelper(Context context) {
        super(context);
        mContext = context;
        Intent intent = new Intent(context, DialogActivity.class);
        context.startActivity(intent);
    }

    public ReverseRegisterNetworkHelper(Context context, String msg) {
        super(context);
        mContext = context;
        Intent intent = new Intent(context, DialogActivity.class);
        intent.putExtra("msgContent", msg);
        context.startActivity(intent);
    }

    @Override
    protected void disposeVolleyError(VolleyError error) {
        notifyErrorHappened(
                "",
                error == null ? "NULL" : error.getMessage());
    }

    @Override
    protected void disposeResponse(JSONObject response) {
        HttpBaseModel httpBaseModel = null;

        /*数据请求完成，发送广播关闭加载框*/
        Intent intent = new Intent("LoadDialoge");
        mContext.sendBroadcast(intent);

        if (response != null) {
            try {
//                String errorCode = response.getString("errorCode");
//                String errorMessage = response.getString("errorMessage");
                String respMsg = response.getString("data");
                String success = response.getString("result");

                if ("true".equals(success)) {
                    httpBaseModel = new HttpBaseModel();
                    httpBaseModel.setErrorCode("1");
                    httpBaseModel.setErrorMessage("请求成功");
                    httpBaseModel.setData(respMsg);
                    httpBaseModel.setResult(success);
                    notifyDataChanged(httpBaseModel);
                } else {
                    notifyErrorHappened("0", respMsg);
                }
            } catch (Exception e) {
                notifyErrorHappened("", "Response format error");
            }
        } else {
            notifyErrorHappened("", "Response is null!");
        }
    }
}
