package com.stucareassignment.handler;

import android.content.Context;
import android.widget.Toast;

public class ItemUserClickHandler {
    Context mContext;

    public ItemUserClickHandler(Context mContext) {
        this.mContext = mContext;
    }

    public void onViewButtonClick(String name) {
        Toast.makeText(mContext, "You clicked: " + name, Toast.LENGTH_SHORT).show();
    }
}
