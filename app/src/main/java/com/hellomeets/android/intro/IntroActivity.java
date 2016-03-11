package com.hellomeets.android.intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.SharedPreferencesCompat;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.hellomeets.android.MainActivity;
import com.hellomeets.android.R;

/**
 * Created by championswimmer on 26/2/16.
 */
public class IntroActivity extends AppIntro {
    @Override
    public void init(@Nullable Bundle savedInstanceState) {

        String helloMeets = "HelloMeets";
        String[] subtitles = getResources().getStringArray(R.array.intro_subtitles);

        Fragment introOne, introTwo, introThree;
        introOne = AppIntroFragment.newInstance(helloMeets, subtitles[0], R.drawable.logo_large, Color.WHITE, Color.BLACK, Color.BLACK);
        introTwo = AppIntroFragment.newInstance(helloMeets, subtitles[1], R.drawable.logo_large, Color.WHITE, Color.BLACK, Color.BLACK);
        introThree = AppIntroFragment.newInstance(helloMeets, subtitles[2], R.drawable.logo_large, Color.WHITE, Color.BLACK, Color.BLACK);

        addSlide(introOne);
        addSlide(introTwo);
        addSlide(introThree);

        setIndicatorColor(Color.BLACK, Color.GRAY);
        setColorDoneText(Color.BLACK);
        setColorSkipButton(Color.BLACK);
        setBarColor(Color.WHITE);
        setSeparatorColor(Color.BLACK);
        setTitleColor(Color.BLACK);

        ImageButton nextButton = (ImageButton) findViewById(R.id.next);
        nextButton.setColorFilter(Color.BLACK);

    }

    @Override
    public void onSkipPressed() {
        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sPrefs.edit().putBoolean(MainActivity.PREF_INTRO_SHOWN, true).apply();
        goToMainActivity();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sPrefs.edit().putBoolean(MainActivity.PREF_INTRO_SHOWN, true).apply();
        goToMainActivity();
    }

    @Override
    public void onSlideChanged() {
    }

    void goToMainActivity() {



        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed = sPrefs.edit();
        ed.putBoolean(MainActivity.PREF_INTRO_SHOWN, true);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(ed);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
