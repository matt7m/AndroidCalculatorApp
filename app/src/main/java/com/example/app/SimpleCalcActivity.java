package com.example.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SimpleCalcActivity extends AppCompatActivity {
    private TextView Screen;
    private Button btnBack, btnAdd, btnSub, btnMlp, btnDiv, btnClear, btnDot, btnChange;
    private Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calculator);

        init_buttons();

        btnBack.setOnClickListener(view -> finish());
    }

    private void init_buttons(){
        btnZero = findViewById(R.id.bt0);
        btnOne = findViewById(R.id.bt1);
        btnTwo = findViewById(R.id.bt2);
        btnThree = findViewById(R.id.bt3);
        btnFour = findViewById(R.id.bt4);
        btnFive = findViewById(R.id.bt5);
        btnSix = findViewById(R.id.bt6);
        btnSeven = findViewById(R.id.bt7);
        btnEight = findViewById(R.id.bt8);
        btnNine = findViewById(R.id.bt9);

        btnBack = findViewById(R.id.bt_back);
        btnAdd = findViewById(R.id.bt_add);
        btnSub = findViewById(R.id.bt_sub);
        btnMlp = findViewById(R.id.bt_mlp);
        btnDiv = findViewById(R.id.bt_div);
        btnDot = findViewById(R.id.bt_dot);
        btnClear = findViewById(R.id.bt_clr);
        btnChange = findViewById(R.id.bt_pm);
    }

}