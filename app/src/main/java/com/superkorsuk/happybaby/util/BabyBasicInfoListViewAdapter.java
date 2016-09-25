package com.superkorsuk.happybaby.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.superkorsuk.happybaby.R;

import java.util.ArrayList;

/**
 * Created by super on 2016-09-25.
 */

public class BabyBasicInfoListViewAdapter extends BaseAdapter {
    public ArrayList<BabyBasicInfo> listViewItemList = new ArrayList<BabyBasicInfo>();

    public BabyBasicInfoListViewAdapter() {
    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.content_baby_basic_info, parent, false);
        }

        ImageView babyImageView = (ImageView) convertView.findViewById(R.id.ivBabyFace);
        TextView babyNameTextView = (TextView) convertView.findViewById(R.id.tvBabyName);
        TextView babyDayTextView = (TextView) convertView.findViewById(R.id.tvBabyDay);

        BabyBasicInfo listViewItem = new BabyBasicInfo();
        listViewItem = listViewItemList.get(position);

        babyImageView.setImageDrawable(listViewItem.getIconDrawable());
        babyNameTextView.setText(listViewItem.getBabyName());
        babyDayTextView.setText(listViewItem.getBabyDay());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    public void addItem(Drawable babyPic, String name, String birth) {
        BabyBasicInfo baby = new BabyBasicInfo();

        baby.setIconDrawable(babyPic);
        baby.setBabyName(name);
        baby.setBabyDay(birth);

        listViewItemList.add(baby);
    }
}
