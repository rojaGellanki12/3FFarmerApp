package com.calibrage.a3ffarmerapp.Activities;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

public class Fontcache {
    public  static HashMap<String, Typeface> fontcahce=new HashMap<>();

    public static Typeface gettypeFace(Context context, String FontName) {


        Typeface typeface = fontcahce.get(FontName);
        if(typeface == null)
        {
            typeface= Typeface.createFromAsset(context.getAssets(), FontName);
            fontcahce.put(FontName,typeface);

        }



        return typeface;
    }
}