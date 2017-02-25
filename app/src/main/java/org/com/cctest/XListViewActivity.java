package org.com.cctest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.lwg.taobaomod.taobaomod.R;

import org.com.cctest.view.XListView;
import org.com.cctest.view.XListView.IXListViewListener;

import java.util.ArrayList;

public class XListViewActivity extends Activity implements IXListViewListener {
	private final String TAG = "XListViewActivity";
	private XListView mListView;
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> items = new ArrayList<String>();
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xlistview);
		geneItems();
		mListView = (XListView) findViewById(R.id.xListView);
		mListView.setPullLoadEnable(true);
		mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
		mListView.setAdapter(mAdapter);
		// mListView.setPullLoadEnable(false);// 开关加载更多
		// mListView.setPullRefreshEnable(false);// 开关刷新功能
		mListView.setXListViewListener(this);
		mHandler = new Handler();
	}

	private void geneItems() {
		for (int i = 0; i != 20; ++i) {
			items.add("refresh cnt " + (++start));
		}
	}

	//加载完毕
	private void onLoad() {
		mListView.stopRefresh();// 停止更新
		mListView.stopLoadMore();// 停止加载更多
		mListView.setRefreshTime("刚刚");// 显示更新时间
	}

	@Override
	public void onRefresh() {
		Log.i(TAG, "刷新最新");
		mHandler.postDelayed(new Runnable() {//延迟调用
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();// 清除数据
				geneItems();// 产生数据
				// mAdapter.notifyDataSetChanged();
				mAdapter = new ArrayAdapter<String>(XListViewActivity.this,
						R.layout.list_item, items);
				mListView.setAdapter(mAdapter);
				onLoad();// 加载完毕
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		Log.i(TAG, "加载更多");
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
				mAdapter.notifyDataSetChanged();
				onLoad();// 加载完毕
			}
		}, 2000);
	}

}