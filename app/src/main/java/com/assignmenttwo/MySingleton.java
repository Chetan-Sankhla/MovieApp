package com.assignmenttwo;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Chetan_Sankhla on 9/20/2016.
 */
public class MySingleton extends Application
{
    private RequestQueue mRequestQueue;
    private static Context mcontext;
    private static MySingleton mInstance;

    MySingleton(Context context)
    {
        mcontext=context.getApplicationContext();
        mRequestQueue=getRequestQueue();
    }
    public static synchronized MySingleton getInstance(Context context)
    {
        if (mInstance==null)
        {
            mInstance=new MySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if (mRequestQueue==null)
        {
            mRequestQueue= Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }
}
