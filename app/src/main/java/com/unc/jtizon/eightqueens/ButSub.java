package com.unc.jtizon.eightqueens;

import android.content.Context;
import android.widget.Button;

/**
 * Created by jtizon on 8/31/17.
 */

public class ButSub extends android.support.v7.widget.AppCompatButton {
    public int rIndex;
    public int cIndex;
//    public ButSub(Context ctx){
//        super(ctx);
//    }
    public ButSub(Context ctx, int i, int j){
        super(ctx);
        rIndex=i;
        cIndex=j;

    }


}
