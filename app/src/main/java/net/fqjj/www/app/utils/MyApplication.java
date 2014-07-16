package net.fqjj.www.app.utils;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joejoeZ
 * @fn Activity管理
 */
public class MyApplication extends Application {

    private static List<Activity> activityList = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private MyApplication() { }


    // 新打开的Activity压入List中
    public static void add(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    // 退出app清除
    public static void exit() {
        for(Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();
        System.exit(0);
    }
}
