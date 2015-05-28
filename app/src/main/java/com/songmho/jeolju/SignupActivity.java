package com.songmho.jeolju;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by songmho on 2015-05-29.
 */
public class SignupActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle("회원가입");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF85E0FF));
        getSupportActionBar().setHomeButtonEnabled(true);

        final EditText email=(EditText)findViewById(R.id.email);
        final EditText name=(EditText)findViewById(R.id.name);
        final EditText password=(EditText)findViewById(R.id.password);
        final EditText pass_check=(EditText)findViewById(R.id.pass_check);
        Button signup=(Button)findViewById(R.id.signup_button);
    }
}
