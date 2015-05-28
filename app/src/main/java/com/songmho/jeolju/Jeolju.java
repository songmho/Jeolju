package com.songmho.jeolju;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

/**
 * Created by songmho on 2015-05-28.
 */
public class Jeolju extends Application{
    private static final String APPLICATION_ID="ordbLs0SR1TIzAiBfJHGCr4IdiaPEJ1gmtjaQb5v";
    private static final String CLIENT_KEY="fmjUScIETxUWAngxMxzbvQQXAH94HXd0Skmtt8Sg";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
        ParseACL defaltACL=new ParseACL();
        defaltACL.setPublicReadAccess(true);
        defaltACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaltACL,true);
    }
}
