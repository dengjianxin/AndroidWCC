package com.maincode.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.risen.androidwcc.R;
import com.maincode.WCCBaseActivity;
import com.maincode.fragment.login.Login1Fragment;
import com.maincode.fragment.login.Login2Fragment;
import com.view.HeadBar;

/**
 * 单页Activity
 */
public class SinglePageActivity extends WCCBaseActivity {
    String tag = "";//用来判断启动哪个Fragment
    String barTitle = ""; //头部Bar的显示内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlepage);
        fragmentManager(tag);
        getParam();
    }

    /**
     * 获取参数
     */
    private void getParam() {
        Intent intent = getIntent();
        if (intent.hasExtra("tag")) {
            tag = intent.getStringExtra("tag");
        } else {
//            headBar.setVisibility(View.GONE);
        }
        if (intent.hasExtra("barTitle")) {
            barTitle = intent.getStringExtra("barTitle");
//            headBar.setBarText(barTitle);
        }
    }

    private void fragmentManager(String tag) {
        if (tag.equals("")) {
            getSupportFragmentManager().beginTransaction().add(R.id.flContent, new Login1Fragment(), "Login1")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null)
                    .commit();
        } else if (tag.equals("Login2")) {
            getSupportFragmentManager().beginTransaction().add(R.id.flContent, new Login2Fragment(), "Login2")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null)
                    .commit();
        }
    }

    public void backFragment() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.go_back_tv:  //返回按钮
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    tag = "";
                    fragmentManager("");
                }
                break;
            case R.id.btnRegistered: //注册按钮
                tag = "Login2";
                fragmentManager("Login2");
                break;
            case R.id.ivWeixin:
                break;
            case R.id.ivQQ:
                break;
            case R.id.ivSin:
                break;
            case R.id.tvLogin:
                break;

        }
    }
}
