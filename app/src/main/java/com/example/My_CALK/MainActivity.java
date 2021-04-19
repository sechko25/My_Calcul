package com.example.My_CALK;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private TextView previousCalculation;
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousCalculation = findViewById(R.id.previousCalculationView);
        display = findViewById(R.id.displayEditText);

        display.setShowSoftInputOnFocus(false);
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursorPos + strToAdd.length());
    }

    public void zeroButPush(View view){
        updateText(getResources().getString(R.string.zeroText));
    }

    public void oneButPush(View view){
        updateText(getResources().getString(R.string.oneText));
    }

    public void twoButPush(View view){
        updateText(getResources().getString(R.string.twoText));
    }

    public void threeButPush(View view){
        updateText(getResources().getString(R.string.threeText));
    }

    public void fourButPush(View view){
        updateText(getResources().getString(R.string.fourText));
    }

    public void fiveButPush(View view){
        updateText(getResources().getString(R.string.fiveText));
    }

    public void sixButPush(View view){
        updateText(getResources().getString(R.string.sixText));
    }

    public void sevenButPush(View view){
        updateText(getResources().getString(R.string.sevenText));
    }

    public void eightButPush(View view){
        updateText(getResources().getString(R.string.eightText));
    }

    public void nineButPush(View view){
        updateText(getResources().getString(R.string.nineText));
    }

    public void multiplyButPush(View view){
        updateText(getResources().getString(R.string.multiplyText));
    }

    public void divideButPush(View view){
        updateText(getResources().getString(R.string.divideText));
    }

    public void subtractButPush(View view){
        updateText(getResources().getString(R.string.subtractText));
    }

    public void addButPush(View view){
        updateText(getResources().getString(R.string.addText));
    }

    public void clearButPush(View view){
        display.setText("");
        previousCalculation.setText("");
    }

    public void decimalButPush(View view){
        updateText(getResources().getString(R.string.decimalText));
    }

    public void equalButPush(View view){
        String userExp = display.getText().toString();
        previousCalculation.setText(userExp);
        char[] strToArray = userExp.toCharArray();

        if(strToArray[strToArray.length-1]=='+' ||
                strToArray[strToArray.length-1]=='-' ||
                strToArray[strToArray.length-1]=='÷' ||
                strToArray[strToArray.length-1]=='×')
        {
            String[] ArrayToStr = new String[strToArray.length];

            for (int i = 0; i < strToArray.length; i++) {
                ArrayToStr[i] = Character.toString(strToArray[i]);
            }

            PolishSolution solution = new PolishSolution();
            int floatReslSol = solution.evalRPN(ArrayToStr);
            System.out.println(floatReslSol);
            String strResSol = Float.toString(floatReslSol);

            display.setText(strResSol);
            display.setSelection(strResSol.length());
        }
        else {
            userExp = userExp.replaceAll(getResources().getString(R.string.divideText), "/");
            userExp = userExp.replaceAll(getResources().getString(R.string.multiplyText), "*");

            Expression exp = new Expression(userExp);
            String result = String.valueOf(exp.calculate());

            display.setText(result);
            display.setSelection(result.length());
        }
    }

    public void deleteButPush(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }
}