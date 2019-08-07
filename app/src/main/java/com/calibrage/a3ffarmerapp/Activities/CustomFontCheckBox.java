package com.calibrage.a3ffarmerapp.Activities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatCheckBox;

public class CustomFontCheckBox extends AppCompatCheckBox {


    public CustomFontCheckBox(Context context) {
        super(context);
        init();
    }

    public CustomFontCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFontCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        Typeface customFont = Fontcache.gettypeFace(getContext(), "fonts/OpenSans-Regular.ttf");
        setTypeface(customFont);
    }
}