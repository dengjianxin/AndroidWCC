package com.maincode.index;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.example.risen.androidwcc.R;
import com.maincode.WCCBaseActivity;
import com.maincode.fragment.ClubFragment;
import com.maincode.fragment.FindFragment;
import com.maincode.fragment.IMFragment;
import com.maincode.fragment.MeFragment;
import com.maincode.fragment.NearbyFragment;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends WCCBaseActivity implements View.OnClickListener {
    FrameLayout flContent;
    FragmentManager fm;
    FragmentTransaction ft;
    private final int NEARBY = 1000;//附近
    private final int CLUB = 1001;//车友会
    private final int FIND = 1002;//发现
    private final int IM = 1003;//消息
    private final int ME = 1004;//我
    NearbyFragment nearbyFragment;
    ClubFragment clubFragment;
    FindFragment findFragment;
    IMFragment imFragment;
    MeFragment meFragment;
    List<Fragment> lf; //保存Fragment用的.因为我导入的v4包没有getFragments这个方法

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        lf = new ArrayList<>();

        flContent = (FrameLayout) findViewById(R.id.flContent);
        init();
    }

    /**
     * 实例化Fragment并且执行初始化工作
     */
    private void init() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        nearbyFragment = NearbyFragment.newInstance(getToken());
        clubFragment = ClubFragment.newInstance(getToken());
        findFragment = FindFragment.newInstance(getToken());
        imFragment = IMFragment.newInstance(getToken());
        meFragment = MeFragment.newInstance(getToken());

        ft.add(R.id.flContent, nearbyFragment, "NearbyFragment");
        ft.show(nearbyFragment);
        ft.add(R.id.flContent, clubFragment, "ClubFragment");
        ft.hide(clubFragment);
        ft.add(R.id.flContent, findFragment, "FindFragment");
        ft.hide(findFragment);
        ft.add(R.id.flContent, imFragment, "IMFragment");
        ft.hide(imFragment);
        ft.add(R.id.flContent, meFragment, "MeFragment");
        ft.hide(meFragment);
        ft.commit();

        lf.add(nearbyFragment);
        lf.add(clubFragment);
        lf.add(findFragment);
        lf.add(imFragment);
        lf.add(meFragment);
    }

    /**
     * 控制Fragment的显示或隐藏 避免刷新
     *
     * @param fragmentTag
     */
    private void fragMentSH(String fragmentTag) {
        for (int i = 0; i < lf.size(); i++) {
            if (lf.get(i).getTag().equals(fragmentTag)) {
                getSupportFragmentManager().beginTransaction().show(lf.get(i)).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(lf.get(i)).commit();
            }
        }
        getSupportFragmentManager().beginTransaction().commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_button0:
                fragMentSH("NearbyFragment");
                break;
            case R.id.radio_button1:
                fragMentSH("ClubFragment");
                break;
            case R.id.radio_button2:
                fragMentSH("FindFragment");
                break;
            case R.id.radio_button3:
                fragMentSH("IMFragment");
                break;
            case R.id.radio_button4:
                fragMentSH("MeFragment");
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }
}
