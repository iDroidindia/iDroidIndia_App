package com.example.santosh.idroidindia;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.logging.Handler;

import static com.example.santosh.idroidindia.R.attr.duration;


public class Main_front_screen extends Activity {

    ImageView android_log;

    TextView tv,tv1;
    TextView a,b,c,d,e,f;
    LinearLayout lay1,lay2,lay3,lay4,lay5,lay6;
    ScrollView scroll;
    ImageView image1,image2,im1,im2,im3,im4,im5,im6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_front_screen);



        tv = (TextView)findViewById(R.id.textView);
        tv1 = (TextView)findViewById(R.id.powerdby);
        image1 = (ImageView)findViewById(R.id.logo);
        image2 = (ImageView)findViewById(R.id.android_logo);

        im1 = (ImageView)findViewById(R.id.im1);
        im2 = (ImageView)findViewById(R.id.im2);
        im3 = (ImageView)findViewById(R.id.im3);
        im4 = (ImageView)findViewById(R.id.im4);
        im5 = (ImageView)findViewById(R.id.im5);
        im6 = (ImageView)findViewById(R.id.im6);


        ImageLoader imageLoader = ImageLoader.getInstance();

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(Main_front_screen.this));
        imageLoader.displayImage("drawable://" + R.drawable.main_logo , image1);
        imageLoader.displayImage("drawable://" + R.drawable.android_icon , image2);

        imageLoader.displayImage("drawable://" + R.drawable.training_icon , im1);
        imageLoader.displayImage("drawable://" + R.drawable.courses_icon , im2);
        imageLoader.displayImage("drawable://" + R.drawable.enquery_icon , im3);
        imageLoader.displayImage("drawable://" + R.drawable.map , im4);
        imageLoader.displayImage("drawable://" + R.drawable.fb1, im5);
        imageLoader.displayImage("drawable://" + R.drawable.call, im6);


        a = (TextView)findViewById(R.id.one);
        b = (TextView)findViewById(R.id.two);
        c = (TextView)findViewById(R.id.three);
        d = (TextView)findViewById(R.id.four);
        e = (TextView)findViewById(R.id.five);
        f = (TextView)findViewById(R.id.six);

        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/engraved_gothic.ttf");
        tv.setTypeface(typeFace);
        tv1.setTypeface(typeFace);
        a.setTypeface(typeFace);
        b.setTypeface(typeFace);
        c.setTypeface(typeFace);
        d.setTypeface(typeFace);
        e.setTypeface(typeFace);
        f.setTypeface(typeFace);


        android_log = (ImageView)findViewById(R.id.android_logo);

        lay1 = (LinearLayout)findViewById(R.id.linear1);
        lay2 = (LinearLayout)findViewById(R.id.linear2);
        lay3 = (LinearLayout)findViewById(R.id.linear3);
        lay4 = (LinearLayout)findViewById(R.id.linear4);
        lay5 = (LinearLayout)findViewById(R.id.linear5);
        lay6 = (LinearLayout)findViewById(R.id.linear6);



        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.android_logo_blink);

        android_log.setAnimation(animation);




        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Training_Programs.class);
                startActivity(i);

            }
        });

        lay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Training_Schedule.class);
                startActivity(i);

            }
        });

        lay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Enquiry_Form.class);
                startActivity(i);

            }
        });




        lay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com/idroidindia"));
                startActivity(i);

            }
        });


        lay6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i;
                i = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 8826417738"));
                startActivity(i);

            }
        });

    }

}
