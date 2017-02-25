package com.lwg.taobaomod.taobaomod.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lwg.taobaomod.taobaomod.R;
import com.lwg.taobaomod.taobaomod.controller.Cons;


/**
 * Created by on 2016/7/27.
 */
public class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 反射视图
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        // 获取参数值
        Bundle b = getArguments();
        String key = b.getString(Cons.Key_Fragment);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(key);
        return view;
    }
}
