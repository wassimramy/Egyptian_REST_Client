package com.wrkhalil.egyptian_rest_client;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class BaseApplication extends Application {

    public static List<Post> postsList;
    public static List<Post> usersList;
    public static RequestQueue queue;
    private static Context context;

    public void onCreate() {
        super.onCreate();
        BaseApplication.context = getApplicationContext();
        queue = Volley.newRequestQueue(BaseApplication.getAppContext());
    }

    public static Context getAppContext() {
        return BaseApplication.context;
    }

}
