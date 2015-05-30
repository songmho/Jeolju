package com.songmho.jeolju;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by songmho on 2015-05-30.
 */
public class AddActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("추가하기");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF85E0FF));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText insert_money=(EditText)findViewById(R.id.insert_money);
        Button add=(Button)findViewById(R.id.add);



    }
}
