package com.songmho.jeolju;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.List;

/**
 * Created by songmho on 2015-05-30.
 */
public class AddActivity extends ActionBarActivity implements View.OnClickListener {
    int cur_year;
    int cur_mon;
    int cur_day;

    TextView text_date;
    EditText beer;
    EditText beer_money;
    EditText soju;
    EditText soju_money;
    EditText cock;
    EditText cock_money;
    EditText etc;
    EditText etc_money;

    int beer_int,beer_money_int;
    int soju_int,soju_money_int;
    int cock_int,cock_money_int;
    int etc_int,etc_money_int;
    int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("추가하기");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF85E0FF));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        beer=(EditText)findViewById(R.id.beer);
        beer_money=(EditText)findViewById(R.id.beer_money);
        soju=(EditText)findViewById(R.id.soju);
        soju_money=(EditText)findViewById(R.id.soju_money);
        cock=(EditText)findViewById(R.id.cock);
        cock_money=(EditText)findViewById(R.id.cock_money);
        etc=(EditText)findViewById(R.id.etc);
        etc_money=(EditText)findViewById(R.id.etc_money);

        Button bt_date=(Button)findViewById(R.id.bt_date);
        Button add=(Button)findViewById(R.id.add);
        text_date=(TextView)findViewById(R.id.text_date);
        text_date.setText("" + Calendar.getInstance().get(Calendar.YEAR) + "." +
                (Calendar.getInstance().get(Calendar.MONTH)+1) + "." +
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        bt_date.setOnClickListener(this);

        add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_date:
                cur_year= Calendar.getInstance().get(Calendar.YEAR);
                cur_mon=Calendar.getInstance().get(Calendar.MONTH);
                cur_day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                Dialog date_picker=new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        text_date.setText(""+cur_year+"."+cur_mon+"."+ cur_day);
                    }
                },cur_year,cur_mon,cur_day);
                date_picker.show();
                break;

            case R.id.add:
                beer_int=Integer.valueOf(String.valueOf(beer.getText()));
                beer_money_int=Integer.valueOf(String.valueOf(beer_money.getText()));
                soju_int=Integer.valueOf(String.valueOf(soju.getText()));
                soju_money_int=Integer.valueOf(String.valueOf(soju_money.getText()));
                cock_int=Integer.valueOf(String.valueOf(cock.getText()));
                cock_money_int=Integer.valueOf(String.valueOf(cock_money.getText()));
                etc_int=Integer.valueOf(String.valueOf(etc.getText()));
                etc_money_int=Integer.valueOf(String.valueOf(etc_money.getText()));
                sum=beer_money_int+soju_money_int+cock_money_int+etc_money_int;
                SharedPreferences preferences=getSharedPreferences("login_info",MODE_PRIVATE);
                String classname=preferences.getString("classname","");
                ParseObject object=new ParseObject(classname);
                object.put("beer",beer_int);
                object.put("beer_money",beer_money_int);
                object.put("soju",soju_int);
                object.put("soju_money",soju_money_int);
                object.put("cock",cock_int);
                object.put("cock_money",cock_money_int);
                object.put("etc",etc_int);
                object.put("etc_money",etc_money_int);
                object.put("year",cur_year);
                object.put("mon", cur_mon);
                object.put("day", cur_day);
                object.put("sum", sum);
                object.saveInBackground();
                ParseUser cur_user=ParseUser.getCurrentUser();
                int cur_money=cur_user.getInt("cur_money");
                cur_money=cur_money+sum;
                cur_user.put("cur_money",cur_money);
                cur_user.saveInBackground();
                Toast.makeText(getApplicationContext(),"총금액 : "+sum+", 추가되었습니다.",Toast.LENGTH_SHORT).show();
                finish();
                break;


        }

    }
}
