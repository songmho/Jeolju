package com.songmho.jeolju;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

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
        final RadioButton male=(RadioButton)findViewById(R.id.male);
        final RadioButton female=(RadioButton)findViewById(R.id.female);
        Button signup=(Button)findViewById(R.id.signup_button);
        final ParseUser cur_user=new ParseUser();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(email.getText()).length()!=0 && String.valueOf(name.getText()).length()!=0 &&
                        String.valueOf(password.getText()).length()!=0 && String.valueOf(pass_check.getText()).length()!=0
                        &&(male.isChecked() || female.isChecked()) ) {
                    cur_user.setUsername(String.valueOf(email.getText()));
                    cur_user.setEmail(String.valueOf(email.getText()));
                    cur_user.put("auditk", Integer.valueOf(0));
                    if (male.isChecked())
                        cur_user.put("ismale", true);
                    else
                        cur_user.put("ismale", false);
                    cur_user.put("name", String.valueOf(name.getText()));
                    if (!(String.valueOf(password.getText()).equals(String.valueOf(pass_check.getText()))))        //비밀번호가 같지 않을 때
                    {
                        Toast.makeText(getApplicationContext(), "비밀번호가 같지 않습니다.", Toast.LENGTH_SHORT).show();
                        Log.d("dfdfd", String.valueOf(email.getText()));
                        Log.d("dfdfd", String.valueOf(pass_check.getText()));
                        Log.d("dfdfdfd",String.valueOf(password.getText()));
                    }
                     else {
                        cur_user.setPassword(String.valueOf(password.getText()));
                        cur_user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(getApplicationContext(),"가입 되었습니다.",Toast.LENGTH_LONG).show();
                                    String email_str=String.valueOf(email.getText());
                                    String[] classname=email_str.split("\\@");
                                    ParseObject object=ParseObject.create(classname[0]);
                                    object.saveInBackground();
                                    SharedPreferences pref_login=getSharedPreferences("login_info", MODE_PRIVATE);
                                    SharedPreferences.Editor editor=pref_login.edit();
                                    editor.putString("email",email_str);
                                    editor.putString("password",String.valueOf(password.getText()));
                                    editor.putString("classname",classname[0]);
                                    editor.putString("name", String.valueOf(name.getText()));
                                    editor.commit();
                                    startActivity(new Intent(SignupActivity.this,MainActivity.class));
                                    finish();
                                }
                                else
                                    Toast.makeText(getApplicationContext(),"dfdfd",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"모든 항목을 채워주세요.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
