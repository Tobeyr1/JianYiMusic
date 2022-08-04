package com.tobery.personalmusic.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @Package: com.example.tobeymusic.BaseApp
 * @ClassName: CrashHandler
 * @Author: Tobey_r1
 * @CreateDate: 2022/2/16 10:26
 * @Description: UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.需要在Application中注册，为了要在程序启动器就监控整个程序。
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/16 10:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = CrashHandler.class.getSimpleName();
    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler 实例
    private static CrashHandler instance;
    private Context mContext;
    //用来存储设备信息和异常信息
    private Map<String,String> infos = new HashMap<>();
    //用于格式化日期，作为日志文件名的一部分
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINESE);

    private CrashHandler(){}

    /**
     * @description 单例
     * @return 返回crashhandler实例
     * @author 13115
     * @time 2022/2/16 10:54
     */
    public static CrashHandler getInstance(){
        if (instance == null) {
            synchronized (CrashHandler.class){
                if (instance == null){
                    instance = new CrashHandler();
                }
            }
        }
        return instance;
    }

    /**
     * @description 初始化
     * @param context context
     * @author 13115
     * @time 2022/2/16 11:14
     */
    public void init(Context context){
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler微程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * @description 当UncaughtException发生时会转入该函数来处理
     * @return
     * @author 13115
     * @time 2022/2/16 10:40
     */
    @Override
    public void uncaughtException(@NonNull  Thread t, @NonNull  Throwable e) {
        if (!handleException(e)&&mDefaultHandler !=null){
            //如果用户没有处理或者init则让系统默认的异常处理器处理
            mDefaultHandler.uncaughtException(t, e);
        }else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            //退出程序
            System.exit(1);
        }

    }

    /**
     * @description 自定义错误处理，收集错误信息，上传错误信息
     * @param ex
     * @return true:处理了该异常信息；false：未处理
     * @author 13115
     * @time 2022/2/16 11:22
     */
    private boolean handleException(final Throwable ex){
        if (ex == null) return false;
        //收集设备参数信息
        collectDeviceInfo(mContext);
        //发送错误日志
        crashReport(ex);
        //Toast提示
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext,ex==null?"":ex.toString(),Toast.LENGTH_SHORT).show();
                Looper.loop();
                Looper.myLooper().quit();
            }
        }.start();
        return true;
    }

    private void crashReport(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\t");
        }
        String time = format.format(new Date());
        //RetrofitUtils.getmApiUrl().report(ex.toString(),time,sb.toString());
    }

    private void collectDeviceInfo(Context mContext) {
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        infos.put("OS Version:", Build.VERSION.RELEASE);
        infos.put("-", Build.VERSION.SDK_INT+"");
        infos.put("Model:", Build.MODEL);
        infos.put("BRAND:", Build.BRAND);
    }
}
