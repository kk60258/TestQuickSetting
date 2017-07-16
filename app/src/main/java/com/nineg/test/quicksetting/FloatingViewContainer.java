package com.nineg.test.quicksetting;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by nineg on 2017/7/16.
 */

public class FloatingViewContainer extends FrameLayout {
    private int mInitWidth, mInitHeight;

    public FloatingViewContainer(@NonNull Context context) {
        this(context, null);
    }

    public FloatingViewContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingViewContainer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FloatingViewContainer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//        setLayoutParams(generateDefaultLayoutParams());
        Drawable d = getResources().getDrawable(R.drawable.ic_settings_applications_white_24dp);
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(d);
        LayoutParams lp = generateDefaultLayoutParams();
        lp.width = mInitWidth = d.getIntrinsicWidth();
        lp.height = mInitHeight = d.getIntrinsicHeight();
        imageView.setLayoutParams(lp);
        addView(imageView);
    }

    private FloatingWindowService.FloatingWindowServiceControl mFloatingWindowControl;

    void setFloatingWindowControl(FloatingWindowService.FloatingWindowServiceControl control) {
        mFloatingWindowControl = control;
    }

    int getFloatingWidth () {
        return mInitWidth;
    }

    int getFloatingHeight () {
        return mInitHeight;
    }
}
