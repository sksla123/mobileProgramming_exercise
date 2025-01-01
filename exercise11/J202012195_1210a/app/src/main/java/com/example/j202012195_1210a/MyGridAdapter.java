package com.example.j202012195_1210a;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MyGridAdapter extends BaseAdapter {
    Context context;
    Integer[] posterID = {
            R.drawable.mov01,
            R.drawable.mov02,
            R.drawable.mov03,
            R.drawable.mov04,
            R.drawable.mov05,
            R.drawable.mov06,
            R.drawable.mov07,
            R.drawable.mov08,
            R.drawable.mov09,
            R.drawable.mov10,
            R.drawable.mov11,
            R.drawable.mov12,
            R.drawable.mov13,
            R.drawable.mov14,
            R.drawable.mov15,
            R.drawable.mov16,
            R.drawable.mov17,
            R.drawable.mov18,
            R.drawable.mov19,
            R.drawable.mov20,
            R.drawable.mov21,
            R.drawable.mov22,
            R.drawable.mov23,
            R.drawable.mov24,
            R.drawable.mov25,
            R.drawable.mov26,
            R.drawable.mov27,
            R.drawable.mov28,
            R.drawable.mov29,
            R.drawable.mov30
    };

    public MyGridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return posterID.length;
    }

    @Override
    public Object getItem(int i) {
        return posterID[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 300));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(5,5,5,5);

        imageView.setImageResource(posterID[i]);

        return imageView;
    }
}
