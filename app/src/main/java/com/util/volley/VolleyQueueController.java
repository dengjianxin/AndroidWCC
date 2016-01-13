package com.util.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by risen on 16/1/8.
 */
public class VolleyQueueController {
    private static RequestQueue mQueue;
    private static Context mContext;
    private static VolleyQueueController volleyQueueController;

    public VolleyQueueController() {
    }

    public static VolleyQueueController getInstance() {
        if (volleyQueueController == null) {
            volleyQueueController = new VolleyQueueController();
        }
        return volleyQueueController;
    }

    public static RequestQueue getRequestQueue(Context context) {
        mContext = context;
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(mContext);
        }
        return mQueue;
    }
}
