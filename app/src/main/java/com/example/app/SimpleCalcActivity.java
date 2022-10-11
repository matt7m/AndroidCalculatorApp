package com.example.app;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SimpleCalcActivity extends AppCompatActivity {
    TextView equationView;
    TextView resultView;

    String equation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calculator);

        equationView = findViewById(R.id.equation_text);
        resultView = findViewById(R.id.result_text);

        equationView.setAutoSizeTextTypeUniformWithConfiguration(
                20, 40, 5, TypedValue.COMPLEX_UNIT_DIP);
        resultView.setAutoSizeTextTypeUniformWithConfiguration(
                20, 50, 5, TypedValue.COMPLEX_UNIT_DIP);

        if (savedInstanceState != null) {
            equation = savedInstanceState.getString("equationValue");

            setEquationValue(equation);
            setResultValue(savedInstanceState.getString("resultValue"));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("equationValue", equation);
        outState.putString("resultValue", resultView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getString("equationValue");
        savedInstanceState.getString("resultValue");
    }

    public void standardOperationOnClick(View view){
        String operation = view.getTag().toString();
        int tempLength = equation.length() - 1;

        if (equation.length() == 0) {
            showToast("Invalid input");
            return;
        } else if (operation.equals("-") && (equation.charAt(tempLength) == '*' || equation.charAt(tempLength) == '/')){
            equation += operation;
        } else if (equation.charAt(tempLength) != '.' && equation.charAt(tempLength) != '+' && equation.charAt(tempLength) != '-'
                && equation.charAt(tempLength) != '*' && equation.charAt(tempLength) != '/') {
            equation += operation;
        } else {
            equation = equation.substring(0, tempLength) + operation;
        }

        setResultValue(equation);
    }

    public void equalOnClick(View view) throws ScriptException {
        if (equation.length() == 0) {
            showToast("Invalid input");
            return;
        }

        String temp = "";
        for(int i = equation.length() - 1; i > 0; i--){
            if (equation.charAt(i) == '/'){
                temp = equation.substring(i+1);
                double tempDouble = Double.parseDouble(temp);
                if (tempDouble == 0){
                    showToast("Do not divide by 0");
                    return;
                }
            }
        }

        if (equation.charAt(equation.length() - 1) == '.' || equation.charAt(equation.length() - 1) == '+' || equation.charAt(equation.length() - 1) == '-'
                || equation.charAt(equation.length() - 1) == '*' || equation.charAt(equation.length() - 1) == '/') {
            equation = equation.substring(0, equation.length() - 1);
        }
        setEquationValue(equation);

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("rhino");

        Object result = scriptEngine.eval(equation);
        equation = result.toString();
        setResultValue(equation);
    }

    public void backspaceOnClick(View view) {
        if (equation.length() == 0) return;

        equation = equation.substring(0, equation.length() - 1);
        setResultValue(equation);
    }

    public void clearOnClick(View view) {
        equation = "";
        setEquationValue(equation);
        setResultValue(equation);
    }

    public void plusminusOnClick(View view) {
        if (equation.length() == 0){
            showToast("Invalid input");
            return;
        }

        if (equation.charAt(0) == '-') {
            equation = equation.substring(1);
        } else {
            equation = "-" + equation;
        }

        setResultValue(equation);
    }

    public void dotOnClick(View view) {
        if (equation.length() == 0) {
            showToast("Invalid input");
            return;
        } else if (equation.charAt(equation.length() - 1) != '.' && equation.charAt(equation.length() - 1) != '+' && equation.charAt(equation.length() - 1) != '-'
                && equation.charAt(equation.length() - 1) != '*' && equation.charAt(equation.length() - 1) != '/') {
            equation += ".";
        } else return;

        setResultValue(equation);
    }

    public void showToast(String text){
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }

    public void setEquationValue(String value) {
        equationView.clearComposingText();
        equationView.setText(value);
    }

    public void setResultValue(String value) {
        resultView.clearComposingText();
        resultView.setText(value);
    }

    public void numbersOnClick(View view){
        String number = view.getTag().toString();
        if (number.equals("0") && equation.length() == 0) return;

        equation += number;
        setResultValue(equation);
    }
}