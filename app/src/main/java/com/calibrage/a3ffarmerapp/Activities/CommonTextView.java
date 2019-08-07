package com.calibrage.a3ffarmerapp.Activities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class CommonTextView extends AppCompatTextView {

    public CommonTextView(Context context) {
        super(context);
        // applying custom font
        ApplyCustomFont(context);
    }

    public CommonTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // applying custom font
        ApplyCustomFont(context);
    }

    public CommonTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // applying custom font
        ApplyCustomFont(context);
    }


    //appliying custom font
    private void ApplyCustomFont(Context context) {
        //change the font style from here by adding the .ttf file to the asserts
        Typeface customFont = Fontcache.gettypeFace(context, "fonts/OpenSans-Regular.ttf");
        setTypeface(customFont);
    }
  //  Typeface faceBold = Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Bold.ttf");

}
