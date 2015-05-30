package com.songmho.jeolju;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by songmho on 2015-05-28.
 */
public class SplashActivity extends Activity {
    int SPLASH_TIME=1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref_login=getSharedPreferences("login_info", MODE_PRIVATE);
                String email = pref_login.getString("email", "");
                String password = pref_login.getString("password", "");
                ParseUser.logInInBackground(email, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if(parseUser!=null) {
                            overridePendingTransition(0, android.R.anim.fade_in);
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                        else{

                            overridePendingTransition(0, android.R.anim.fade_in);
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });
            }
        },SPLASH_TIME);
    }
}
