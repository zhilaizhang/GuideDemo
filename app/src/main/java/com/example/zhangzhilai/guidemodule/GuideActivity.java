package com.example.zhangzhilai.guidemodule;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

/**
 * Created by zhangzhilai on 1/8/15.
 */
public class GuideActivity extends FragmentActivity {

    public static final String TAG = "GuideActivity";

    private final int mGuideNums = 3;
    private ViewPager mGuideViewPager;
    private GuidePagerAdapter mGuidePagerAdapter;
    private Integer[] mGuideBackGrounds;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_activity);
        findViews();
        initData();
    }

    private void findViews() {
        mGuideViewPager = (ViewPager)findViewById(R.id.guide_viewpager);
    }

    private void initData() {
        mGuideBackGrounds = new Integer[mGuideNums];
        setGuideBakcgRounds();
        mGuidePagerAdapter = new GuidePagerAdapter(getSupportFragmentManager(), mGuideBackGrounds);
        mGuideViewPager.setAdapter(mGuidePagerAdapter);
        mGuideViewPager.setOffscreenPageLimit(1);
    }

    private void setGuideBakcgRounds() {
        mGuideBackGrounds[0] = R.drawable.ico_loading_guide1;
        mGuideBackGrounds[1] = R.drawable.ico_loading_guide2;
        mGuideBackGrounds[2] = R.drawable.ico_loading_guide3;
    }


}
