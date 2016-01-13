package com.maincode.index;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.risen.androidwcc.R;
import com.view.DialogActivity;

public class ScrollingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setClass(this, DialogActivity.class);
        intent.putExtra("msgContent", "正在执行操作");
        startActivity(intent);
    }
}
