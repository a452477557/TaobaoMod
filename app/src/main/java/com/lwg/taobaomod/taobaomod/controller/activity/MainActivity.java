package com.lwg.taobaomod.taobaomod.controller.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwg.taobaomod.taobaomod.R;
import com.lwg.taobaomod.taobaomod.controller.Cons;
import com.lwg.taobaomod.taobaomod.controller.fragment.Manager;

public class MainActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    public static String KEY_WORD = "";//输入框的关键词
    // 按钮数组
    private RelativeLayout[] arrBtn = new RelativeLayout[4];
    // 标签文字数组
    private TextView[] arrTxt = new TextView[4];
    // 标签下划线(Indicator)
    private RelativeLayout[] arrLine = new RelativeLayout[4];

    // 滑动页容器
    private ViewPager viewPager;

    // 顶部栏(包含沉浸到状态栏的部分)
    RelativeLayout topbar;

    // 选中的标签颜色
    int color_selected = R.color.btn_blue_normal;
    // 未选中的标签颜色
    int color_unselected = R.color.common_top_bar_normal;
    // 顶部栏颜色
    int color_topbar = R.color.btn_blue_normal;

    // 管理者
    Manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //透明状态栏(沉浸式)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏(沉浸式)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_main);
        setSearchView(); //搜索框
        manager = new Manager(this);
        initStyle();
        initView();
        initData();
        initListener();



        viewPager.setCurrentItem(0);
        setColor(0);
    }

    private void setSearchView() {


    }

    // 改变颜色
    private void initStyle() {
        color_topbar = R.color.orange;
        color_selected = R.color.orange;
    }

    private void initView() {

        // 初始化下划线(逐帧动画)
        String packageName = getApplicationContext().getPackageName();//获取当前包名
        for (int i = 0; i < 4; i++) {
            //从图片名称反射资源ID  R.id.line1
            int id = this.getResources().getIdentifier("line" + (i + 1), "id", packageName);
            arrLine[i] = (RelativeLayout) findViewById(id);
            int id2 = this.getResources().getIdentifier("btn" + (i + 1), "id", packageName);
            arrBtn[i] =(RelativeLayout) findViewById(id2);
            int id3 = this.getResources().getIdentifier("txt" + (i + 1), "id", packageName);
            arrTxt[i] = (TextView) findViewById(id3);
            // 设置标签名
            arrTxt[i].setText(Cons.Tab_Name[i]);
        }

        // 获取ViewPager对象
        viewPager = (ViewPager) findViewById(R.id.vp);
        topbar = (RelativeLayout) findViewById(R.id.titlebar);

        topbar.setBackgroundColor(getResources().getColor(color_topbar));
    }

    private void initData() {
        manager.initData(viewPager);
    }

    private void initListener() {
        // 添加按钮的监听
        for (int i = 0; i < arrBtn.length; i++) {
            arrBtn[i].setOnClickListener(this);
        }
        // 添加滑动页的监听
        viewPager.setOnPageChangeListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                viewPager.setCurrentItem(0);// 第一页
                break;
            case R.id.btn2:
                viewPager.setCurrentItem(1);// 第二页
                break;
            case R.id.btn3:
                viewPager.setCurrentItem(2);// 第二页
                break;
            case R.id.btn4:
                viewPager.setCurrentItem(3);// 第二页
                break;
            default:
                break;
        }

    }

    /**
     * 1.将所有的背景统一颜色
     * 2.将当前选中的背景设置特殊颜色
     * @param index
     */
    public void setColor(int index){
        // "所有人"都回复最初的状态
        for (int i = 0; i<arrBtn.length; i++){
            arrLine[i].setBackgroundColor(Color.WHITE);
            arrTxt[i].setTextColor(getResources().getColor(color_unselected));
        }
        arrLine[index].setBackgroundColor(getResources().getColor(color_selected));// 特殊
        arrTxt[index].setTextColor(getResources().getColor(color_selected));
    }


    @Override
    public void onPageScrolled(int i, float v, int i2) {
        // 滑动过程中...(写动画)
    }

    @Override
    public void onPageSelected(int i) {// 核心事件
        // 页面的选中(当前的页面已经显示了90%)
        setColor(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        // 滑动的状态改变
    }


}
