package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SimpleCalcActivity extends AppCompatActivity {
    TextView equationView;
    TextView resultView;

    String equation = "";
    int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calculator);

        initTextViews();
    }

    public void initTextViews(){
        equationView = findViewById(R.id.equation_text);
        resultView = findViewById(R.id.result_text);
    }

    public void setValue(String value){
        equation = equation + value;
        equationView.setText(equation);
    }

    public void equalOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");


        try {
            result = (double)engine.eval(equation);
        } catch(ScriptException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }

        if (result != null){
            resultView.setText(String.valueOf(result.doubleValue()));
        }
    }

    public void additionOnClick(View view) {
        setValue("+");
    }

    public void subtractionOnClick(View view) {
        setValue("-");
    }

    public void divisionOnClick(View view) {
        setValue("/");
    }

    public void multiplicationOnClick(View view) {
        setValue("*");
    }

    public void plusminusOnClick(View view) {
        //setValue("1");
    }

    public void dotOnClick(View view) {
        setValue(",");
    }

    public void clearOnClick(View view) {
        equationView.setText("");
        equation = "";
        resultView.setText("");
    }

    public void zeroOnClick(View view) {
        setValue("0");
    }

    public void oneOnClick(View view) {
        setValue("1");
    }

    public void twoOnClick(View view) {
        setValue("2");
    }

    public void threeOnClick(View view) {
        setValue("3");
    }

    public void fourOnClick(View view) {
        setValue("4");
    }

    public void fiveOnClick(View view) {
        setValue("5");
    }

    public void sixOnClick(View view) {
        setValue("6");
    }

    public void sevenOnClick(View view) {
        setValue("7");
    }

    public void eightOnClick(View view) {
        setValue("8");
    }

    public void nineOnClick(View view) {
        setValue("9");
    }

    public void backOnClick(View view) {

    }
}