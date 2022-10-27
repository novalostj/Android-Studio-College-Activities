package com.example.activity7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultPage extends AppCompatActivity {

    TextView txtGender, txtFullName, txtBmi, txtResult, txtUnderweight, txtHealthy, txtOverweight, txtObese;
    TextView txtMain;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        txtGender = findViewById(R.id.txt_Gender);
        txtFullName = findViewById(R.id.txt_FullName);
        txtBmi = findViewById(R.id.txt_bmi);
        txtResult = findViewById(R.id.txt_Result);
        txtUnderweight = findViewById(R.id.txt_underweight);
        txtHealthy = findViewById(R.id.txt_healthy);
        txtOverweight = findViewById(R.id.txt_overweight);
        txtObese = findViewById(R.id.txt_obese);

        Intent intent = getIntent();

        String fName = intent.getStringExtra("fName");
        String mi = intent.getStringExtra("mi");
        String lName = intent.getStringExtra("lName");
        String gender = intent.getStringExtra("gender");
        float weight = Float.parseFloat(intent.getStringExtra("weight"));
        float height = Float.parseFloat(intent.getStringExtra("height"));

        float bmi = bmiCalculator(height, weight);
        String result = bmiMsg(bmi);
        CheckTextsColors();

        txtGender.setText(gender);
        txtFullName.setText(SetFullName(fName, mi, lName));
        txtResult.setText(result);
        txtBmi.setText(decimalFormat.format(bmi));
    }

    public String SetFullName (String fName, String mi, String lName){
        return fName + " " + mi + " " + lName;
    }

    public float bmiCalculator(float height, float weightKG){
        height /= 100;
        return weightKG / (height * height);
    }

    public String bmiMsg(float bmi){
        if (bmi < 18.5) {
            txtMain = txtUnderweight;
            return "Underweight";
        }
        else if (bmi < 25) {
            txtMain = txtHealthy;
            return "Healthy Weight";
        }
        else if (bmi < 30) {
            txtMain = txtOverweight;
            return "Overweight";
        }
        txtMain = txtObese;
        return "Obese";
    }

    public void CheckTextsColors(){
        ColorText(txtUnderweight, Color.RED);
        ColorText(txtHealthy, Color.GREEN);
        ColorText(txtOverweight, Color.RED);
        ColorText(txtObese, Color.RED);
    }

    public void ColorText(TextView txtCurrent, int color){
        if (txtCurrent == txtMain){
            txtResult.setTextColor(color);
            txtCurrent.setBackgroundColor(color);
            return;
        }
        txtCurrent.setBackgroundColor(getResources().getColor(R.color.transparent));
    }
}