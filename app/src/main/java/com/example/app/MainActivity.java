package com.example.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private Button simple_calc;
    private Button advanced_calc;
    private Button about_button;
    private Button exit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simple_calc = findViewById(R.id.button1);
        advanced_calc = findViewById(R.id.button2);
        about_button = findViewById(R.id.button3);
        exit_button = findViewById(R.id.button4);

        simple_calc.setOnClickListener((view) -> {
            startActivity(new Intent(MainActivity.this, SimpleCalcActivity.class));
        });

        advanced_calc.setOnClickListener((view) -> {
            startActivity(new Intent(MainActivity.this, AdvancedCalcActivity.class));
        });

        about_button.setOnClickListener((view) -> {
            startActivity(new Intent(MainActivity.this, AboutInfoActivity.class));
        });

        exit_button.setOnClickListener((view) -> {
            finish();
            System.exit(0);
        });

    }
}