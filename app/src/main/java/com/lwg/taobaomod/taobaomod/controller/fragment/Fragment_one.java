package com.lwg.taobaomod.taobaomod.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lwg.taobaomod.taobaomod.R;
import com.lwg.taobaomod.taobaomod.controller.Constans;
import com.lwg.taobaomod.taobaomod.controller.adapter.PostAdapter;
import com.lwg.taobaomod.taobaomod.controller.entity.Post;
import com.lwg.taobaomod.taobaomod.controller.entity.PostList;
import com.lwg.taobaomod.taobaomod.controller.util.Utils;

import org.com.cctest.view.XListView;

import java.util.ArrayList;
import java.util.List;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;


public class Fragment_one extends Fragment implements PostAdapter.IControl, XListView.IXListViewListener {

    List<Post> list = new ArrayList<Post>();
    ArrayList<String> PicList;
    XListView lv;
    PostAdapter adapter;
    int pageno = 1;// 页码
    int n = 10; //每页显示的信息数目
    String ps=""; //默认模糊查询



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_one,null);

        initView(view);
        initData(pageno);
        return view;
    }

    private void initData(int pno) {
        int p=(pno-1)*n;
        RequestParams params = new RequestParams();
        params.addBodyParameter("p",""+p);
        params.addBodyParameter("n",""+n);
        params.addBodyParameter("ttype","2000");
        params.addBodyParameter("subject",ps);
        new HttpUtils().send(HttpRequest.HttpMethod.POST,
                Constans.URL_POSTLIST_ALL,params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> info) {
                        String strJosn = info.result;
                        Log.i("gang", "onSuccess: "+strJosn);

                        // 下面的代码是为了解决php的输出Bug
                         strJosn = strJosn.substring(strJosn.indexOf("{"));
                       PostList data = JSON.parseObject(strJosn, PostList.class);
                        list.addAll(data.getList());// 续接数据
                        Log.i(TAG, "onSuccess: ______________"+ list.addAll(data.getList()));
                        adapter.setList(list);
                        PicList = Utils.getPicList(list);// 获取图片列表
                        adapter.notifyDataSetChanged();// 显示数据
                        onLoadCompleted();
                    }
                    @Override
                    public void onFailure(HttpException e, String s) {
                     /*   Utils.show("连接失败:" + s);
                        Log.i("spl", "连接失败:" + s);*/
                    }
                });
    }
    private void initView(View view) {
        lv = (XListView) view.findViewById(R.id.xListView);
        adapter = new PostAdapter(getActivity());
        adapter.setControl(this);
        lv.setAdapter(adapter);

        lv.setPullLoadEnable(true);// 开关加载更多
        lv.setPullRefreshEnable(true);// 开关刷新功能
        lv.setXListViewListener(this);// 设置监听
    }



    /**
     * 更新数据
     */
    public void update(){
        Utils.print("更新数据");
        initData(pageno);
    }

    @Override
    public void handleImageClick(Post item) {
        // 发送到浏览大图界面
       /* Intent intent = new Intent(this, DragImageActivity.class);
        intent.putStringArrayListExtra(Constans.KEY_piclist, PicList);
        int pos = Utils.getPicIndex(PicList,item);
        intent.putExtra(Constans.KEY_picindex, pos);
        Utils.show("发送前:pos="+pos);
        startActivity(intent);*/

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==100){
            update();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRefresh() {
        // 更新
        pageno = 1;// 页码归为
        list.clear();// 记录清空-------------
        initData(pageno);// 重新调用异步请求
    }

    @Override
    public void onLoadMore() {
        // 加载更多(翻页)
        pageno++;// 往下翻页
        initData(pageno);
        // 追加下一页的数据

    }

    // 上次加载时间
    long loadTime = System.currentTimeMillis();

    Handler mHandler= new Handler();

    private void onLoadCompleted() {
        mHandler.postDelayed(new Runnable() {//延迟调用
            @Override
            public void run() {
                // 加载完毕,处理UI
                lv.stopRefresh();// 停止更新
                lv.stopLoadMore();// 停止加载更多
                lv.setRefreshTime(Utils.getTime(loadTime));// 显示更新时间
                loadTime = System.currentTimeMillis();// 上次更新时间
            }
        }, 500);

    }
}
