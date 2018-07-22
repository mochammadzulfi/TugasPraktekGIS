package com.a.dev.polseksurabaya;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class Splash extends AppCompatActivity {

    //lama interval
    private static int splashinterval = 6000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//menghilangkan title bar
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//menghilangkan statusbar
        setContentView(R.layout.actifity_splash);

        //set gradient colour on toolbar
        //getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark));

        //menghapus text title bar
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        //setTitle Toolbar
        //getSupportActionBar().setTitle("");

        eksekusi();
    }

    protected void eksekusi() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, splashinterval);
    }
    public void onBackPressed() {

    }
}

