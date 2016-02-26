package com.hellomeets.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hellomeets.android.intro.IntroActivity;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_INTRO_SHOWN = "intro_shown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean introShown = sPrefs.getBoolean(PREF_INTRO_SHOWN, false);

        if (!introShown) {
            Intent i = new Intent(this, IntroActivity.class);
            startActivity(i);
            finish();
        }

        setContentView(R.layout.activity_main);
    }
}
