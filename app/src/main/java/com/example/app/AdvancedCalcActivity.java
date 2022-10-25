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

public class AdvancedCalcActivity extends AppCompatActivity {
    TextView equationView;
    TextView resultView;

    String equation = "";
    Boolean powerFuncState = false, advancedOperationState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_calculator);

        equationView = findViewById(R.id.equation_text);
        resultView = findViewById(R.id.result_text);

        equationView.setAutoSizeTextTypeUniformWithConfiguration(
                20, 40, 5, TypedValue.COMPLEX_UNIT_DIP);
        resultView.setAutoSizeTextTypeUniformWithConfiguration(
                20, 50, 5, TypedValue.COMPLEX_UNIT_DIP);

        if (savedInstanceState != null) {
            equation = savedInstanceState.getString("equationValue");
            powerFuncState = savedInstanceState.getBoolean("powerFuncState");
            advancedOperationState = savedInstanceState.getBoolean("advancedOperationState");

            setEquationValue(equation);
            setResultValue(savedInstanceState.getString("resultValue"));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("equationValue", equation);
        outState.putString("resultValue", resultView.getText().toString());
        outState.putBoolean("powerFuncState", powerFuncState);
        outState.putBoolean("advancedOperationState", advancedOperationState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getString("equationValue");
        savedInstanceState.getString("resultValue");
        savedInstanceState.getBoolean("powerFuncState");
        savedInstanceState.getBoolean("advancedOperationState");
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

    public void specialOperationOnClick(View view) {
        String operation = view.getTag().toString();
        String tempValue;
        int pointer = 0;

        if (equation.length() == 0) {
            showToast("Invalid input");
            return;
        } else if (advancedOperationState) {
            showToast("Finish another advanced operation");
        } else if (equation.charAt(equation.length() - 1) == '+' || equation.charAt(equation.length() - 1) == '-'
                || equation.charAt(equation.length() - 1) == '*' || equation.charAt(equation.length() - 1) == '/') {
            showToast("Invalid operation");
            return;
        } else {
            for (int i = equation.length() - 1; i > 0; i--){
                if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '*' || equation.charAt(i) == '/'){
                    pointer = i;
                    break;
                }
            }

            String[] tempTab;
            tempTab = createSpecialEquation(equation, operation, pointer);
            tempValue = tempTab[0];
            equation = tempTab[1];

            advancedOperationState = true;
            setResultValue(tempValue);
        }
    }

    public String[] createSpecialEquation(String equation, String operation, int value){
        String tempString = equation.substring(value);
        String tempValue = "";

        if (operation.equals("pow")){
            tempValue = equation.substring(0, value) + operation + "(" + tempString + ")";
            equation = equation.substring(0, value) + "Math." + operation + "(" + tempString  + ", 2)";
        } else {
            tempValue = equation.substring(0, value) + operation + "(" + tempString + ")";
            equation = equation.substring(0, value) + "Math." + operation + "(" + tempString  + ")";
        }

        return new String[]{tempValue, equation};
    }

    public void powerOfNumberOnClick(View view) {
        String tempValue = "";
        int pointer = 0;

        System.out.println(powerFuncState);

        if (powerFuncState){
            equation += ")";
            setResultValue(equation);
            powerFuncState = false;
            return;
        }

        if (equation.length() == 0) {
            showToast("Invalid input");
        } else if (equation.charAt(equation.length() - 1) == '+' || equation.charAt(equation.length() - 1) == '-'
                || equation.charAt(equation.length() - 1) == '*' || equation.charAt(equation.length() - 1) == '/') {
            showToast("Invalid operation");
        } else {
            for (int i = equation.length() - 1; i > 0; i--){
                if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '*' || equation.charAt(i) == '/'){
                    pointer = i;
                    break;
                }
            }
            String tempString = equation.substring(pointer);
            tempValue = equation.substring(0, pointer) + "pow(" + tempString + ",";
            equation = equation.substring(0, pointer) + "Math.pow(" + tempString + ",";

            powerFuncState = true;
            setResultValue(tempValue);
        }
    }

    public void equalOnClick(View view) throws ScriptException {
        if (equation.length() == 0) {
            showToast("Invalid input");
            return;
        }

        if (powerFuncState){
            showToast("Finish power operation");
            return;
        }

        String temp = "";
        for (int i = equation.length() - 1; i >= 0; i--){
            if (equation.charAt(i) == '/'){
                temp = equation.substring(i + 1);
                double tempDouble = Double.parseDouble(temp);
                if (tempDouble == 0){
                    showToast("Do not divide by 0");
                    return;
                }
            }
            if (equation.charAt(i) == '-' && i != 0){
                if (equation.charAt(i) == '-' && equation.charAt(i - 1) == '(' && equation.charAt(i - 2) == 'g'
                        && equation.charAt(i - 3) == 'o' && equation.charAt(i - 4) == 'l') {
                    showToast("Invalid negative number");
                    return;
                }
                if (equation.charAt(i) == '-' && equation.charAt(i - 1) == '(' && equation.charAt(i - 2) == 'n' && equation.charAt(i - 3) == 'l') {
                    showToast("Invalid negaitve number");
                    return;
                }
                if (equation.charAt(i) == '-' && equation.charAt(i - 1) == '(' && equation.charAt(i - 2) == 't' && equation.charAt(i - 3) == 'r'
                        && equation.charAt(i - 4) == 'q' && equation.charAt(i - 5) == 's') {
                    showToast("Invalid negative number");
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

        if (advancedOperationState) advancedOperationState = false;
        setResultValue(equation);
    }

    public void backspaceOnClick(View view) {
        if (equation.length() == 0) return;

        equation = equation.substring(0, equation.length() - 1);
        setResultValue(equation);
    }

    public void clearOnClick(View view) {
        equation = "";
        powerFuncState = false;
        advancedOperationState = false;
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
        }

        setResultValue(equation);
    }

    public void showToast(String text){
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }

    public void setEquationValue(String value) {
        equationView.clearComposingText();
        if (value.equals(""))
            equationView.setText(value);
        else
            equationView.setText(value + "=");
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