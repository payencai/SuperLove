package com.love.novalue.tools;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class MarginUtils {

    public static void setMargin(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static int getStatusBarHeight(Context context) {
        int height = 0;
        int resourceId = context.getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = context.getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

}

