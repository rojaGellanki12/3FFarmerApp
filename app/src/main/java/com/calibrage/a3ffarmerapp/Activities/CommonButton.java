package com.calibrage.a3ffarmerapp.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class CommonButton extends Button {
    public CommonButton(Context context) {
        super(context);
        // appliying custom font
        ApplyCustomFont(context);
    }

    public CommonButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // appliying custom font
        ApplyCustomFont(context);

    }

    public CommonButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // appliying custom font
        ApplyCustomFont(context);
    }

    // appliying custom font
    private void ApplyCustomFont(Context context) {
        //change the font style from here by adding the .ttf file to the asserts
        Typeface customFont = Fontcache.gettypeFace(context, "fonts/OpenSans-Regular.ttf");
        setTypeface(customFont);
    }
}
