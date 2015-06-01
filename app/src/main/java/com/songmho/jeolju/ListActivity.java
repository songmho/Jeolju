package com.songmho.jeolju;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmho on 2015-05-30.
 */
public class ListActivity extends ActionBarActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String classname;
    LinearLayout cur_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF85E0FF));
        getSupportActionBar().setTitle("내기록");

        SharedPreferences pref=getSharedPreferences("login_info",MODE_PRIVATE);
        classname=pref.getString("classname","");

        recyclerView=(RecyclerView)findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        final List<RecyclerItem> items=new ArrayList<>();
        if(ParseUser.getCurrentUser()!=null){
            ParseQuery<ParseObject> road_data=ParseQuery.getQuery(classname);
            road_data.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    for(ParseObject o:list){
                        RecyclerItem item=new RecyclerItem(o.getInt("beer"),o.getInt("beer_money"),
                                o.getInt("soju"),o.getInt("soju_money"),o.getInt("cock"),o.getInt("cock_money"),
                                o.getInt("etc"),o.getInt("etc_money"),o.getInt("sum"),o.getInt("year"),o.getInt("mon"),
                                o.getInt("day"));
                        items.add(item);
                    }
                    recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),items,R.layout.item_list));
                }
            });
        }
    }
}
