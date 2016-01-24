package com.example.chisty.techcrunchfeed;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by chisty on 1/24/2016.
 */
public class Helper {

    public static void Log(String message){
        Log.d("Logger", message);
    }

    public static void Show(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
