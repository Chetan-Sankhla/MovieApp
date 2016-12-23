package com.assignmenttwo;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by Chetan_Sankhla on 9/19/2016.
 */public class MovieAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mtitle;
    private  ArrayList<String> mImage;
    MovieAdapter(Context context,ArrayList<String> title,ArrayList<String> image)
    {
        mContext=context;
        mtitle = title;
        mImage = image;

    }
    // ViewHolder pattern
    private static class ViewHolder {
        SimpleDraweeView mImageViewMovie;
        TextView mTextViewMovie;
    }

    @Override
    public int getCount() {
        return mtitle.size();
    }

    @Override
    public Object getItem(int position) {
        return mtitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        ViewHolder holder;
        if (view == null)
       {
           holder = new ViewHolder();
           view=inflater.inflate(R.layout.gridview_image,parent,false);
           holder.mImageViewMovie= (SimpleDraweeView) view.findViewById(R.id.imageViewMovie);
           holder.mTextViewMovie=(TextView)view.findViewById(R.id.textViewMovie);
           view.setTag(holder);
       }
        else
           holder= (ViewHolder) view.getTag();

        holder.mTextViewMovie.setText(mtitle.get(position));
        holder.mImageViewMovie.setImageURI(Uri.parse("https://image.tmdb.org/t/p/w185"+mImage.get(position)));

        return view;
    }
}