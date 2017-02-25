package com.lwg.taobaomod.taobaomod.controller;

/**
 * Created by 孙沛林 on 2015/12/14.
 */
public class Constans {

    // 本地地址(基础地址)
    //public static final String URL_BASE = "http://192.168.5.3/";
    // 远程服务器地址(基础地址)
    public static final String URL_BASE = "http://www.codepower.cn/";
    // 登录访问接口
    public static final String URL_LOGIN = URL_BASE + "logindo.php";
    // 检查用户名重复的接口
    public static final String URL_CHKUNAME = URL_BASE + "checkuname.php";
    // 注册新用户的接口
    public static final String URL_REG = URL_BASE + "registerdo.php";

    // 获取用户信息的接口
    public static final String URL_USER = URL_BASE + "user.php";

    // 获取用户列表的接口
    public static final String URL_USERLIST = URL_BASE + "userlist.php";

    // 获取帖子列表的接口
    public static final String URL_POSTLIST = URL_BASE + "postlist.php";

    // 获取帖子列表的接口(更宽松的)
    public static final String URL_POSTLIST_ALL = URL_BASE + "taobaolistall.php";

    // 获取热门帖子列表的接口
    public static final String URL_POSTLIST_HOT = URL_BASE + "hotpostlist.php";

    // 上传用户头像修改密码的接口
    public static final String URL_AVATAR = URL_BASE + "avatardo.php";

    // 发表帖子的接口,包括回帖的接口
    public static final String URL_POST_SUBMIT = URL_BASE + "insertpost.php";

    /** 获取回帖列表的接口地址 */
    public static final String URL_REPOSTLIST= URL_BASE +"repostlist.php";

    /** 添加关注 接口地址 */
    public static final String URL_GUANZHU= URL_BASE +"insertguanzhu.php" ;

    /** 取消关注 接口地址 */
    public static final String URL_GUANZHU_DEL= URL_BASE +"deleteguanzhu.php" ;

    /** 获取单个帖子详情的接口地址 */
    public static String URL_POST= URL_BASE +"post.php" ;

    // 获取是否关注的信息
    public static final String URL_GUANZHU_IF = URL_BASE + "guanzhu.php";

    // 当前用户的id
    public static int UID = 0;
    // 当前用户的用户名
    public static String UNAME = "";
    // 当前用户的头像地址
    public static String AVATAR = "";

    // 分享首选项的文件名
    public static final String SP_FILE = "userloglin";

    // 分享首选项的用户名
    public static final String SP_UNAME = "SP_UNAME";
    // 分享首选项的密码
    public static final String SP_UPASS = "SP_UPASS";
    // 分享首选项的时间
    public static final String SP_TIME = "SP_TIME";

    /** 使用照相机拍照获取图片 */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    /** 使用相册中的图片 */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    //系统剪裁界面的请求码
    public static final int PHOTO_REQUEST_CUT = 3;
    //帖子详细界面的请求码
    public static final int REQUEST_POST_DETAIL = 4;
    //发表回帖界面的请求码
    public static final int REQUEST_REPOST_DETAIL = 5;
    //更换头像的请求码
    public static final int REQUEST_CHANGE_AVATAR = 6;
    //piclist
    public static final String KEY_piclist = "piclist";
    //KEY_picindex
    public static final String KEY_picindex = "KEY_picindex";
    //主贴实体类
    public static final String KEY_POST = "KEY_POST";
    //"^[a-zA-Z0-9_-\u4e00-\u9fa5]{2,15}$"
    public static final String REGX_UNAME = "^[a-zA-Z0-9\\u4e00-\\u9fa5_-]{2,15}$";
    //二维码的文本内容
    public static final String KEY_QR = "KEY_QR";

    //查看代码内容
    public static final String KEY_CODE = "KEY_CODE";


}
