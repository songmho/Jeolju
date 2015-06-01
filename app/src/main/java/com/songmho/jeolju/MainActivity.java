package com.songmho.jeolju;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.ParseUser;

public class MainActivity extends ActionBarActivity {
    TextView cur_money;
    TextView goal_money;
    ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF85E0FF));

        //객체 선언
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        LinearLayout drawer=(LinearLayout)findViewById(R.id.drawer);
       // final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager;
        RecyclerView.Adapter adapter;
        ImageButton add=(ImageButton)findViewById(R.id.add);
        TextView name=(TextView)findViewById(R.id.name);
        cur_money=(TextView)findViewById(R.id.cur_money);
        goal_money=(TextView)findViewById(R.id.goal_money);

        //변수 선언
        String[] drawer_list_list= new String[]{"내기록","AUDIT-K","설정","절주앱"};


        //객체 사용

        //money
            onResume();

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

        SharedPreferences pref=getSharedPreferences("login_info",MODE_PRIVATE);
        name.setText(pref.getString("name",""));

        //progress bar

        //drawer
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),drawer_list_list,R.layout.item_drawerlist));


        //add 버튼
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int cur_money_int= ParseUser.getCurrentUser().getInt("cur_money");
        int goal_money_int=ParseUser.getCurrentUser().getInt("goal_money");
        cur_money.setText(""+cur_money_int+"원");
        goal_money.setText("" + goal_money_int + "원");
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
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
