package com.example.activity6;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GridCard extends BaseAdapter {

    DisplayMetrics displayMetrics;
    Context context;
    String[] strList;
    TypedArray intList;

    float width;
    float height;

    public GridCard(Context context, String[] strList, TypedArray intList, DisplayMetrics displayMetrics) {
        this.context = context;
        this.strList = strList;
        this.intList = intList;
        this.displayMetrics = displayMetrics;

        width = (displayMetrics.widthPixels / 3 - 12);
        height = ((width / 250) * 282);
    }

    @Override
    public int getCount() {
        return strList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        View lView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);

        lView.setLayoutParams(new LinearLayout.LayoutParams(Math.round(width), Math.round(height)));
        TextView textView = lView.findViewById(R.id.textView);

        textView.setText(strList[i]);
        lView.setBackgroundResource(intList.getResourceId(i, 0));

        lView.setOnClickListener(view -> ToastMessage(strList[i]));

        return lView;
    }

    private void ToastMessage(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
