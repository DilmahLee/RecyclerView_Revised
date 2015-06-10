package com.example.lee.recyclerview_revised;

import android.view.View;

/**
 * Created by Lee on 2015/6/11.
 */
public interface OnItemRecycleViewClickListener {
    //public void onItemClicked(int position, MyAdapter mAdapter);
    public void onItemClicked(View view, int position);
}
