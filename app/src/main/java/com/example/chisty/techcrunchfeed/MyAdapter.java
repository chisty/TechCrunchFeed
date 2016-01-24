package com.example.chisty.techcrunchfeed;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chisty on 1/24/2016.
 */
class MyAdapter extends BaseAdapter {

    public MyAdapter(Context context, ArrayList<HashMap<String, String>> dataSource){
        this.dataSource= dataSource;
        this.context= context;
        this.inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(dataSource == null) return  0;
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        if(dataSource == null) return null;
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
                MyHolder holder;
        View row= convertView;
        if(row == null){
            row= inflater.inflate(R.layout.custom_row, parent, false);
            holder= new MyHolder(row);
            row.setTag(holder);
        }
        else{
            holder= (MyHolder) row.getTag();
        }

        Uri imageURL;
        HashMap<String, String> currentItem= dataSource.get(position);
        holder.articleTitleText.setText(currentItem.get("title"));
        holder.articlePublishedDateText.setText(currentItem.get("pubDate"));
        holder.articleDescriptionText.setText(currentItem.get("description"));
        try {
            imageURL = Uri.parse(currentItem.get("imageURL"));
            holder.articleImage.setImageURI(imageURL);
        }catch (Exception ex){
            Helper.Log("Error= "+ ex.getMessage());
        }


        return row;
    }

    ArrayList<HashMap<String, String>> dataSource;
    Context context;
    LayoutInflater  inflater;
}
