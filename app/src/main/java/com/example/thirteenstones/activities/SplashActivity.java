package com.example.thirteenstones.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thirteenstones.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode (Build.VERSION.SDK_INT < 28 ?
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY :AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }
}