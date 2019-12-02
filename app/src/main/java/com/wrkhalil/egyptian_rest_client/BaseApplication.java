package com.wrkhalil.egyptian_rest_client;

import android.app.Application;
import android.content.Context;

import java.util.List;

public class BaseApplication extends Application {

    public static List<Post> postsList;
    public static List<Post> usersList;
    private static Context context;

    public void onCreate() {
        super.onCreate();
        BaseApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return BaseApplication.context;
    }

}
