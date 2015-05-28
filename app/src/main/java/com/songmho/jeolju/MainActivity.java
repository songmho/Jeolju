package com.songmho.jeolju;

import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    ActionBarDrawerToggle drawerToggle;
    Fragment cur_fragment=new Fragment();
    int MAX_PAGE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF85E0FF));

        //객체 선언
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        LinearLayout drawer=(LinearLayout)findViewById(R.id.drawer);

        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);

        ImageButton add=(ImageButton)findViewById(R.id.add);


        //객체 사용

        //드로어
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

        //뷰페이저
        viewPager.setAdapter(new viewpager_adapter(getSupportFragmentManager()));

        //add 버튼
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t=Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
                t.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private class viewpager_adapter extends FragmentPagerAdapter {
        @Override
        public Fragment getItem(int position) {
            if(position<0 || position>=MAX_PAGE)
                return null;
            switch (position){
                case 0:
                    cur_fragment=new DialogFragment();
                    break;
                case 1:
                    cur_fragment=new ListFragment();
                    break;

            }
            return cur_fragment;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }

        public viewpager_adapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }
    }
}
