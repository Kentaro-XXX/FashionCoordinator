package com.example.kentaro.fashioncoordinator.fashionSelector;

import android.util.Log;

/**
 * Created by Kentaro on 2015/09/01.
 */
public interface FashionSelector {
   static final int FASHION_PATTERN_NUM = 7;

    /* Get the number of fashion pattern */
    public int getPatternNumber();

    /* Get fashion path based on the input index */
    public String getBottomsPath(int index);
    public String getTopsPath(int index);
}

