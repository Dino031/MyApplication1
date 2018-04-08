package com.example.myapplication1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView textViewControl;
    TextView textViewResult;
    private String display = "";
    private String hDisplay = "";
    private String result = "";
    private String op = "";
    private double num1, num2, num3;
    private boolean opS = true, bPoint = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewControl = (TextView) findViewById(R.id.textViewControl);
        textViewControl.setText(display);

        textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewResult.setText(result);

    }

    public void updateTextViewControl() {
        textViewControl.setText(display);
    }

    public void updateTextViewResult() {
        textViewResult.setText(result);
    }

    public void onClickNumber(View v) {
        result = "";
        updateTextViewResult();
        Button b = (Button) v;
        display += b.getText();
        hDisplay += b.getText();
        updateTextViewControl();
    }

    public void onClickPoint(View v) {
        if (display == "") return;
        Button b = (Button) v;
        if (!hDisplay.contains(".") & hDisplay!="") {
            display += b.getText();
            hDisplay += b.getText();
            updateTextViewControl();
        }
    }

    public void onClickOperator(View v) {
        Button b = (Button) v;

        if (opS) {
            op = b.getText().toString();
            if (result == "") {
                if (display == "") return;
                num1 = Double.parseDouble(hDisplay);
                display += b.getText();
                updateTextViewControl();
                hDisplay = "";
            } else {
                num1 = Double.parseDouble(result);
                display = result;
                display += b.getText();
                updateTextViewControl();
            }
            opS = false;
        } else {
            onClickEqual(null);
            op = b.getText().toString();
            if (result != "") {
                num1 = num3;
            }
            display = num1 + op + "";
            updateTextViewControl();
            opS = false;
        }
    }

    public void onClickEqual(View v) {
        if (display == "" || hDisplay == "") return;
        num2 = Double.parseDouble(hDisplay);

        switch (op) {
            case "+":
                num3 = num1 + num2;
                break;
            case "-":
                num3 = num1 - num2;
                break;
            case "*":
                num3 = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    num3 = num1 / num2;
                } else {
                    clear();
                    Toast.makeText(this, "Don't divide with 0", Toast.LENGTH_LONG).show();
                    updateTextViewControl();
                    updateTextViewResult();
                    return;
                }

                break;
        }

        clear();
        result = round(num3, 2) + "";
        updateTextViewControl();
        updateTextViewResult();
    }

    public void clear() {
        display = "";
        result = "";
        hDisplay = "";
        opS = true;
    }

    public void onClickClear(View v) {
        clear();
        updateTextViewControl();
        updateTextViewResult();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

} // END activity_main
