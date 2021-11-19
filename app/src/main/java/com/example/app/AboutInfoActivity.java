package com.example.app;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutInfoActivity extends AppCompatActivity {
    private TextView appName;
    private TextView appAuthor;
    private TextView appDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_info);

        appName = findViewById(R.id.app_name);
        appName.setText("'First Calculator'");

        appAuthor = findViewById(R.id.author_text);
        appAuthor.setText("Mateusz Murawski");

        appDescription = findViewById(R.id.description);
        appDescription.setText("First simple and advanced calculator application developed in Android.");
    }
}
