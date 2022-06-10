package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.projets6.activity.activity_settings;
import com.example.projets6.activity.logInActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;

public class loading_screen extends AppCompatActivity{
    Button start_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        Button start=(Button) findViewById(R.id.button_start);
        start.setOnClickListener(v -> change());
        start_button=(Button) findViewById(R.id.button_start);
        Animation animation= AnimationUtils.loadAnimation(loading_screen.this,R.anim.blink_anim);
        start_button.startAnimation(animation);


    }



    public void change(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }




}
