package com.maincode.fragment.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.risen.androidwcc.R;
import com.maincode.activity.SinglePageActivity;
import com.util.HttpUrl;
import com.util.volley.ReverseRegisterNetworkHelper;
import com.util.volley.UIDataListener;
import com.view.HeadBar;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login2Fragment extends Fragment implements View.OnClickListener, UIDataListener {

    HeadBar headBar;
    View v;
    EditText edtPhone;
    SinglePageActivity activity;

    public Login2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (SinglePageActivity) getActivity();
        v = inflater.inflate(R.layout.fragment_login2, container, false);
        init();
        return v;
    }

    private void init() {
        headBar = (HeadBar) v.findViewById(R.id.headBar);
        headBar.onclick(this);
        headBar.setBarText("注册");
        headBar.setBarRightText("下一步");
        headBar.setBarRightSH(View.VISIBLE);
        edtPhone = (EditText) v.findViewById(R.id.edtPhone);
    }

    private void setVerification() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        JsonRequest jsonRequest=new JsonRequest(Request.Method.POST,HttpUrl.AVAILABLEPHONE,) {
//            @Override
//            protected Response parseNetworkResponse(NetworkResponse networkResponse) {
//                return null;
//            }
//
//            @Override
//            public int compareTo(Object another) {
//                return 0;
//            }
//        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl.AVAILABLEPHONE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("log", "response -> " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("log", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("phone", edtPhone.getText().toString().trim());
                return map;
            }
        };
        requestQueue.add(stringRequest);
        Map<String, String> params = new ArrayMap<>();
        params.put("phone", edtPhone.getText().toString().trim());
        ReverseRegisterNetworkHelper networkHelper = new ReverseRegisterNetworkHelper(getActivity());
        networkHelper.setUiDataListener(this);
        networkHelper.sendPostRequest(HttpUrl.AVAILABLEPHONE, params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back_tv:
                activity.backFragment();
                break;
            case R.id.tvBarRight:
                if (edtPhone.getText().toString().trim().length() > 10) {
                    setVerification();
                } else {
                    activity.showToast("请输入正确的手机号码");
                }
                break;
        }
    }

    @Override
    public void onDataChanged(Object data) {
        Log.i("Data", data.toString());
    }

    @Override
    public void onErrorHappened(String errorCode, String errorMessage) {

    }
}
