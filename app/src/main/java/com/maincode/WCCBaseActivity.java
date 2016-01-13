package com.maincode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.risen.androidwcc.R;
import com.maincode.model.HttpBaseModel;
import com.maincode.model.UserInfo;
import com.util.SystemParams;
import com.util.volley.UIDataListener;
import com.util.volley.VolleyQueueController;

public class WCCBaseActivity extends FragmentActivity implements UIDataListener<HttpBaseModel> {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null) getActionBar().hide();
        sharedPreferences = getSharedPreferences("Users", 0);
        editor = sharedPreferences.edit();
    }

    /*获取用户名*/
    public String getUserName() {
        return sharedPreferences.getString("userName", "");
    }

    /*获取密码 后续系统自动登陆用*/
    public String getPwd() {
        return sharedPreferences.getString("password", "");
    }

    /*获取token*/
    public String getToken() {
        return sharedPreferences.getString("token", "");
    }

    /**
     * 登陆成功后保存信息
     *
     * @param user 登陆成功后传进来的userInfo实体类
     */
    public void saveUserInfo(UserInfo user) {
        editor.putString("userName", user.userName);
        editor.putString("password", user.password);
        editor.putString("token", user.token);
        editor.commit();
    }

    /**
     * 土司提示
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDataChanged(HttpBaseModel data) {
        if (data.getResult().equals("true")) {
            showToast("操作成功");
        }
    }

    @Override
    public void onErrorHappened(String errorCode, String errorMessage) {
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.sysmsgtitle));
        alertDialog.setMessage(SystemParams.getInstance().getErrorMsg("errorMessage"));
        alertDialog.setButton(0, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }
}
