package com.example.volleydemo.volleysupport;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application
{
    public RequestQueue mRequestQeue;
public static AppController mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;

    }

    public static synchronized AppController getInstance()
    {
        return mInstance;
    }

    public RequestQueue getRequestQeue()
    {
        if (mRequestQeue==null)
        {
            mRequestQeue= Volley.newRequestQueue(getApplicationContext());
        }
        return getRequestQeue();
    }
}
