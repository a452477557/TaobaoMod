package com.lwg.taobaomod.taobaomod.controller.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lwg.taobaomod.taobaomod.R;
import com.lwg.taobaomod.taobaomod.controller.Constans;
import com.lwg.taobaomod.taobaomod.controller.entity.Post;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 孙沛林 on 2015/12/7.
 * 工具类
 */
public class Utils {

    public static void setContext(Context context) {
        Utils.context = context;
    }

    public static boolean debug = true;// 调试开关
    public static boolean debug_ui = true; // 前台的调试开关

    private Utils(){}

    public static Context context;

    public static void init(Context cxt){
        context = cxt;
        initLoader(cxt);

    }

    /**
     * ImageLoader的全局参数配置
     * @param context
     */
    public static void initLoader(Context context){
        // ImageLoader的全局参数配置 建造者模式
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)// 线程的优先级别
                .denyCacheImageMultipleSizesInMemory() // 拒绝不同的缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())// 对临时文件名加密
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb SDCard上的缓存空间
                .tasksProcessingOrder(QueueProcessingType.LIFO)// 任务队列采取LIFO
                .writeDebugLogs() //调试日志-可在项目发布时删除
                .build();// 构建配置
        //初始化ImageLoader 单例模式
        ImageLoader.getInstance().init(config);
    }

    /**
     * 图片显示选项的设置
     * @return
     */
    public static DisplayImageOptions getImageOption(){
        DisplayImageOptions options= new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                        //.displayer(new CircleBitmapDisplayer(Color.WHITE, 5))//圆形特效
                .build();
        return options;
    }
    /**
     * 短时间显示Toast
     */
    public static void show(String msg){
        if (debug_ui && debug) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void print(String msg){
        if (debug) {
            if (debug_ui) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }

            Log.i("spl", msg);
        }

    }

    public static void print(Context context, String msg){

         //Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
         Log.i("spl", msg);
    }



    private static BitmapUtils bitmapUtils = null;

    public static BitmapUtils getInstance(){
        if (bitmapUtils == null) {
            bitmapUtils = new BitmapUtils(context);
            bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_launcher);
            bitmapUtils.configDefaultLoadFailedImage(R.mipmap.ic_launcher);
            bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);

            bitmapUtils.configMemoryCacheEnabled(false);
            bitmapUtils.configDiskCacheEnabled(true);
        }

        return bitmapUtils;
    }

    /**
     * 项首选项中存入一个键值对

     * @param filename 文件名
     * @param key
     * @param value
     */
    public static void writeData(String filename,
                                 String key,String value){
        //实例化SharedPreferences对象,参数1是存储文件的名称，参数2是文件的打开方式，当文件不存在时，直接创建，如果存在，则直接使用
        SharedPreferences mySharePreferences
                =context.getSharedPreferences(filename, Activity.MODE_PRIVATE);

        //实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor =mySharePreferences.edit();

        //用putString的方法保存数据
        editor.putString(key, value);

        //提交数据
        editor.commit();
    }

    /**
     * 从首选项中取出一个值

     * @param filename 文件名
     * @param key
     * @return
     */
    public static String readData(String filename,
                                  String key){
        //实例化SharedPreferences对象,参数1是存储文件的名称，参数2是文件的打开方式，当文件不存在时，直接创建，如果存在，则直接使用
        SharedPreferences mySharePreferences
                =context.getSharedPreferences(filename, Activity.MODE_PRIVATE);
        //用getString获取值
        return mySharePreferences.getString(key, "");
    }

    /**
     * 查询某个key是否已经存在
     * @param filename
     * @param key
     * @return
     */
    public static boolean contains(String filename, String key)
    {
        SharedPreferences sp =
                context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 移除某个值
     * @param filename
     * @param key
     */
    public static void remove(String filename, String key)
    {
        SharedPreferences sp = context.getSharedPreferences(filename,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 日期格式转换
     * @param millis
     * @return
     */
    public static String getTime(long millis){
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }



    /** 保存方法 */
    public static void saveBitmap(Bitmap bm, String picName) {
        Log.e("spl", "保存图片-->"+picName);
        File f = new File(picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i("spl", "已经保存");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 检验用户名是否符合规则
     * @param userName
     * @param regex
     * @return
     */
    public static boolean checkUserName(String userName,String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(userName);
        return m.matches();
    }

    /**
     * 验证字符串的长度
     * @param str
     * @param min
     * @param max
     * @return
     */
    public static boolean checkStringlength(String str,int min,int max){
        str = str.trim();// 去掉空格
        if (str.length()<min || str.length()>max){
            return false;
        }else{
            return true;
        }
    }


    /**
     * 从全集中筛选图片列表
     *
     * @param list
     * @return
     */
    public static ArrayList<String> getPicList(List<Post> list) {
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getPic().equals("")) {
                // 添加到结果集
                res.add(Constans.URL_BASE + list.get(i).getPic());
            }
        }
        return res;
    }

    public static int getPicIndex(ArrayList<String> picList, Post item) {
        int res = 0;
        for (int i = 0; i < picList.size(); i++) {
            if (picList.get(i).equals(Constans.URL_BASE +item.getPic())){// 相同
                return i;
            }
        }
        return res;
    }

   /* *//**
     * 根据提供的字符串,生成相应的二维码位图对象
     * @param text 字符串
     * @param QR_WIDTH 位图宽度
     * @param QR_HEIGHT 位图高度
     * @return 二维码位图对象
     *//*
    public static Bitmap createQRImage(
            String text, int QR_WIDTH,int QR_HEIGHT) {

     *//*   Bitmap bitmap = null;
        try {
            // 需要引入core包
            QRCodeWriter writer = new QRCodeWriter();
            // 获取文本内容


            Log.i("spl", "生成的文本：" + text);


            // 把输入的文本转为二维码
            BitMatrix martix = writer.encode(text, BarcodeFormat.QR_CODE,
                    QR_WIDTH, QR_HEIGHT);// 宽度,高度

            System.out.println("w:" + martix.getWidth() + "h:"
                    + martix.getHeight());

            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
                    BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }

                }
            }

            bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
                    Bitmap.Config.ARGB_8888);

            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);

            System.out.println(Environment.getExternalStorageDirectory());



        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;*//*
    }*/

    public static String getFormatDate(long time) {
        java.util.Date date = new java.util.Date(time);
        SimpleDateFormat dateFormat
                = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String getImagePath(String path){

        if (path.indexOf("http://")!=0){
            path = Constans.URL_BASE + path;
        }
        return path;
    }

    /**
     * 日期格式转换
     * @param millis
     * @return

    public static String getTime(long millis){
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    } */



}
