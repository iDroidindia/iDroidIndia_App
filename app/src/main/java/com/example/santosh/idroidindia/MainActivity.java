package com.example.santosh.idroidindia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;


public class MainActivity extends Activity {

    ImageView image1,image2,image3;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.text1);
        tv2 = (TextView)findViewById(R.id.text2);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/engraved_gothic.ttf");
        tv1.setTypeface(typeFace);
        tv2.setTypeface(typeFace);

        image1 = (ImageView)findViewById(R.id.imageView2);
        image2 = (ImageView)findViewById(R.id.imageView3);
        image3 = (ImageView)findViewById(R.id.imageView4);

        ShimmerFrameLayout layout = (ShimmerFrameLayout) findViewById(R.id.shimmerlayout);
        layout.startShimmerAnimation();



        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_1);

        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image2);
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image3);


        image1.startAnimation(animation);
        image2.startAnimation(animation2);
        image3.startAnimation(animation3);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(MainActivity.this, Main_front_screen.class);
                startActivity(i);

                // close this activity
                finish();
            }

        }, 4500);


    }



}
