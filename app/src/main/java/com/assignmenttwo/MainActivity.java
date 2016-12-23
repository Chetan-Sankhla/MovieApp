package com.assignmenttwo;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.facebook.drawee.backends.pipeline.Fresco;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    //Initialisation of things.
    ArrayList<String> titleList;
    ArrayList<String> imagePath;
    FrameLayout container;
    TabLayout tabLayout;
    ViewPager viewPager;
    UpcomingFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=(TabLayout)findViewById(R.id.tabs);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        setupTabs();
        tabLayout.setupWithViewPager(viewPager);
        titleList=new ArrayList<>();
        imagePath=new ArrayList<>();
        container= (FrameLayout) findViewById(R.id.container);
        Fresco.initialize(this);
        fragment=new UpcomingFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).addToBackStack(null).commit();
    }
    private void setupTabs()
    {
        ViewPagerAdapter mAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
    }
    public static class ViewPagerAdapter extends FragmentPagerAdapter
    {
        public ViewPagerAdapter(FragmentManager fm)
        {super(fm);}

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return new UpcomingFragment();
                case 1:
                    return new TopMoviesFragment();
                case 2:
                    return new PopularMoviesFragment();
                default:
                    return new UpcomingFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return "Upcoming";
                case 1:
                    return "Top Rated";
                case 2:
                    return "Popular";
                default:
                    return "Upcoming";

            }
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        SharedPreferences sharedPreferences=getSharedPreferences("X",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Last Activity: ",getClass().getName());
        editor.commit();
    }
    @Override
    protected void onRestart()
    {
        super.onRestart();
    }
}
