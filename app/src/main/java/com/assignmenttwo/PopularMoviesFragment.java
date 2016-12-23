package com.assignmenttwo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PopularMoviesFragment extends Fragment
{
    GridView gridView;
    String TAG="Popular";
    MovieAdapter adapter;
    ArrayList<String> titleList;
    ArrayList<String> imagePath;
    ArrayList<Integer> MovieIdList;
    public static final String URL_POPULAR="http://api.themoviedb.org/3/movie/popular?api_key=b758f3d7c7663123b6c693a2232eb117";
    public PopularMoviesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup cont,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_popular_movies,cont,false);
        gridView=(GridView)view.findViewById(R.id.gridViewPopular);
        titleList=new ArrayList<>();
        imagePath=new ArrayList<>();
        MovieIdList=new ArrayList<>();
        getdata();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"You clicked on: "+titleList.get(position),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),MovieDetails.class);
                Log.d(TAG, "onItemClick: "+MovieIdList.get(position));
                intent.putExtra("id",MovieIdList.get(position));
                startActivity(intent);
            }
        });
        return view;
    }


    private void getdata()
    {
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, URL_POPULAR, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    parseJson(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(getActivity()).addToRequestQueue(objectRequest);
    }
    private void parseJson(JSONObject response) {
        JSONArray jsonArray = null;
        try {
            jsonArray = response.getJSONArray("results");

            Log.d(TAG, "showJson: ");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject movieObject = jsonArray.getJSONObject(i);
                MovieIdList.add(movieObject.getInt("id"));
                titleList.add(movieObject.getString("title"));
                imagePath.add(movieObject.getString("poster_path"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new MovieAdapter(getActivity(), titleList, imagePath);
        gridView.setAdapter(adapter);
    }

}
