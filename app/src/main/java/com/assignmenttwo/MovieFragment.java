package com.assignmenttwo;

import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;


public class MovieFragment extends Fragment
{
    private static final String TAG=MovieFragment.class.getSimpleName();
    SimpleDraweeView image;
    TextView titleText;
    String mImage,mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImage=getArguments().getString("poster");
        mTitle=getArguments().getString("title");
        Log.d(TAG, mImage+mTitle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_movie_details,container,false);
        image= (SimpleDraweeView) view.findViewById(R.id.moviePoster);
        titleText=(TextView)view.findViewById(R.id.title);
        image.setImageURI(Uri.parse("https://image.tmdb.org/t/p/w185"+mImage));
        titleText.setText(mTitle);
        return view;
    }
}