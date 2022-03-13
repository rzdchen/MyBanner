package com.cc.mybanner.lab.indicator;

import android.content.res.Resources;
import android.util.TypedValue;

public class DisplayUtil {
    public static int dp2px(float dp, Resources res){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }
}
