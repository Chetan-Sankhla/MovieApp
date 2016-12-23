package com.assignmenttwo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class MovieDetails extends AppCompatActivity
{
    String TAG="Movie Details";
    SimpleDraweeView PosterImage;
    TextView TitleText,OverviewText;
    String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int Id=getIntent().getIntExtra("id",0);
        Log.d(TAG, "onCreate: "+Id);
        PosterImage=(SimpleDraweeView)findViewById(R.id.image);
        TitleText= (TextView) findViewById(R.id.titleText);
        OverviewText=(TextView)findViewById(R.id.overviewText);
        URL="http://api.themoviedb.org/3/movie/"+Id+"?api_key=b758f3d7c7663123b6c693a2232eb117";
        Log.d(TAG, "onCreate: "+URL);
        RequestData();
    }

    public void RequestData()
    {
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    fillData(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        MySingleton.getInstance(this).addToRequestQueue(objectRequest);
    }
    public void fillData(JSONObject response)
    {
        try {
            PosterImage.setImageURI(Uri.parse("https://image.tmdb.org/t/p/w342"+response.getString("poster_path")));
            TitleText.setText(response.getString("original_title"));
            OverviewText.setText(response.getString("overview"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
