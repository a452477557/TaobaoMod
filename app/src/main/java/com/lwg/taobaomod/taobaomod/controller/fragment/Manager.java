package com.lwg.taobaomod.taobaomod.controller.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.lwg.taobaomod.taobaomod.controller.Cons;
import com.lwg.taobaomod.taobaomod.controller.adapter.MyFragmentAdapater;

import java.util.ArrayList;
import java.util.List;


/**
 * 管理对碎片的生成,传参和加载
 * Created by on 2016/7/27.
 */
public class Manager {

    Activity context;

    public Manager(Activity context){
        this.context = context;
    }

    // 碎片集合
    private List<Fragment> fragments;
    // 碎片适配器
    private MyFragmentAdapater adapter;

    public void initData(ViewPager viewPager) {
        // 准备碎片
        fragments = new ArrayList<>();
        fragments.add(new Fragment_one());
        fragments.add(new Fragment_one());
        fragments.add(new Fragment_one());
        fragments.add(new Fragment_one());
        for (int i = 0; i< fragments.size(); i++){
            Bundle b = new Bundle();
            b.putString(Cons.Key_Fragment,Cons.Tab_Name[i]);
            fragments.get(i).setArguments(b);
        }
        // 实例化适配器
        adapter = new MyFragmentAdapater(((FragmentActivity)context).getSupportFragmentManager());
        adapter.setFragments(fragments);// 添加数据
        viewPager.setAdapter(adapter);// 关联适配器
    }


}


