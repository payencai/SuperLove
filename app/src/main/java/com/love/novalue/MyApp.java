package com.love.novalue;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.love.novalue.net.NetUtils;
import com.tencent.smtt.sdk.QbSdk;


public class MyApp extends MultiDexApplication {
    public static boolean isLogin=false;
    public static String token="";
    public static MyApp context;

    public static MyApp getInstance(){
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        init();
        initRouter(this);
    }
    private void init(){
        context=this;
        NetUtils.getInstance().initNetWorkUtils(this);//okgo初始化
        initX5();

    }
    private void initX5(){
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }
    private void initRouter(Application context){

        ARouter.openLog();     // Print log
        ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        ARouter.init(context); // As early as possible, it is recommended to initialize in the Application
    }



    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }


}
