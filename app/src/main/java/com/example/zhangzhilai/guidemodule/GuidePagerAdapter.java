package com.example.zhangzhilai.guidemodule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by zhangzhilai on 1/8/15.
 */
public class GuidePagerAdapter extends FragmentPagerAdapter {

    public static final String TAG = "GuidePagerAdapter";

    private Integer[] mBackGroundResourceIds;

    public GuidePagerAdapter(FragmentManager fm, Integer[] data){
        super(fm);
        mBackGroundResourceIds = data;
    }

    @Override
    public int getCount(){
        return mBackGroundResourceIds.length;
    }

    @Override
    public Fragment getItem(int position) {
        GuidViewFragment guidViewFragment = GuidViewFragment.newInstance(mBackGroundResourceIds[position], mBackGroundResourceIds.length,position);
        return guidViewFragment;
    }
}
