package com.lwg.taobaomod.taobaomod.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 碎片适配器
 * Created  on 2016/7/7.
 */
public class MyFragmentAdapater extends FragmentPagerAdapter {

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    // 碎片集合
    private List<Fragment> fragments;

    /**
     * 构造器
     * @param fm 碎片管理者对象
     */
    public MyFragmentAdapater(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);// 返回当前碎片元素
    }

    @Override
    public int getCount() {
        return fragments.size();// 返回集合大小
    }
}
