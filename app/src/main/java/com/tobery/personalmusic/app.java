package com.tobery.personalmusic;

import android.app.Application;
import androidx.multidex.MultiDex;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.BlackToastStyle;
import com.tobery.personalmusic.util.CrashHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package: com.tobery.personalmusic
 * @ClassName: App
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/11 0:57
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/11 0:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class app extends Application {

    private final CountDownLatch mCountDownLatch = new CountDownLatch(1);

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private static final int CORE_POOL_SIZE = Math.max(2,Math.min(CPU_COUNT-1,4));

    private static app instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ToastUtils.init(this, new BlackToastStyle());
        CrashHandler.getInstance().init(this);
        ExecutorService pool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        pool.submit(new Runnable() {
            @Override
            public void run() {
                MultiDex.install(instance);
                mCountDownLatch.countDown();
            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
             //   StarrySky.init(instance).apply();

            }
        });
        try {
            //如果await之前没有调用countDown那么就会一直阻塞在这里
            mCountDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
