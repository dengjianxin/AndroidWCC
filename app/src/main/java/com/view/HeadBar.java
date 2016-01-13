package com.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.risen.androidwcc.R;

/**
 * Created by risen on 16/1/11.
 * 头部公共空间
 */
public class HeadBar extends RelativeLayout {
    LayoutInflater layoutInflater;
    View v;
    TextView go_back_tv, tvBarRight, tvBarTitle;
    RelativeLayout rlBar;

    public HeadBar(Context context) {
        super(context);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = layoutInflater.inflate(R.layout.view_headbar, null);
        addView(v);
        findView();
    }

    public HeadBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = layoutInflater.inflate(R.layout.view_headbar, null);
        addView(v);
        findView();
    }

    private void findView() {
        go_back_tv = (TextView) v.findViewById(R.id.go_back_tv);
        tvBarRight = (TextView) v.findViewById(R.id.tvBarRight);
        tvBarTitle = (TextView) v.findViewById(R.id.tvBarTitle);
        rlBar = (RelativeLayout) v.findViewById(R.id.rlBar);
    }

    /**
     * 控制返回按钮的显示或者隐藏
     *
     * @param i
     */
    public void setBackSH(int i) {
        go_back_tv.setVisibility(i);
    }

    /**
     * 返回按钮赋值
     *
     * @param title
     */
    public void setBackText(String title) {
        go_back_tv.setText(title);
    }

    /**
     * 给头部标题赋值
     *
     * @param title
     */
    public void setBarText(String title) {
        tvBarTitle.setText(title);
    }

    /**
     * 给头部Bar右侧控件赋值
     *
     * @param title
     */
    public void setBarRightText(String title) {
        tvBarRight.setText(title);
    }

    /**
     * 控制头部Bar右侧控件的显示或者隐藏
     *
     * @param i
     */
    public void setBarRightSH(int i) {
        tvBarRight.setVisibility(i);
    }

    /**
     * 设置右侧控件的字体大小
     *
     * @param size
     */
    public void setBarRightTextSize(int size) {
        tvBarRight.setTextSize(size);
    }

    /**
     * 设置头部背景颜色
     *
     * @param color
     */
    public void setBarBackGroundColor(String color) {
        rlBar.setBackgroundColor(Color.parseColor(color));
    }

    /**
     * 设置Bar按钮的点击事件
     *
     * @param onClickListener
     */
    public void onclick(OnClickListener onClickListener) {
        go_back_tv.setOnClickListener(onClickListener);
        tvBarRight.setOnClickListener(onClickListener);
    }
}
