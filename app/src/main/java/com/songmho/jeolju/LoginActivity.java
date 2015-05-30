package com.songmho.jeolju;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by songmho on 2015-05-29.
 */
public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText email=(EditText)findViewById(R.id.email);
        final EditText password=(EditText)findViewById(R.id.password);
        Button login=(Button)findViewById(R.id.login);
        Button signup=(Button)findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(String.valueOf(email.getText()), String.valueOf(password.getText())
                        , new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            String email_str=String.valueOf(email.getText());
                            String[] classname=email_str.split("\\@");
                            SharedPreferences pref_login=getSharedPreferences("login_info", MODE_PRIVATE);
                            SharedPreferences.Editor editor=pref_login.edit();
                            editor.putString("email",email_str);
                            editor.putString("password",String.valueOf(password.getText()));
                            editor.putString("classname",classname[0]);
                            editor.putString("name", (String) ParseUser.getCurrentUser().get("name"));
                            editor.commit();
                            Toast.makeText(getApplicationContext(),"로그인 되었습니다.",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }
}
