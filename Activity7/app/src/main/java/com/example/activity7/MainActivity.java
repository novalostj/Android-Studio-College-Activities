package com.example.activity7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    EditText txtFName, txtMI, txtLName, txtHeight, txtWeight;
    RadioButton radMale, radFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFName = findViewById(R.id.in_firstName);
        txtMI = findViewById(R.id.in_mI);
        txtLName = findViewById(R.id.in_lastName);
        radMale = findViewById(R.id.rad_male);
        radFemale = findViewById(R.id.rad_female);
        txtHeight = findViewById(R.id.in_heightCm);
        txtWeight = findViewById(R.id.in_weightKG);

        findViewById(R.id.btn_bmi).setOnClickListener(view -> {

            String fName = txtFName.getText().toString();
            String mi = txtMI.getText().toString();
            String lName = txtLName.getText().toString();
            String height = txtHeight.getText().toString();
            String weight = txtWeight.getText().toString();
            String gender = radMale.isChecked() ? "Mr." : radFemale.isChecked() ? "Mrs." : "No Gender Selected";

            Intent bmiIntent = new Intent(this, ResultPage.class);

            bmiIntent.putExtra("fName", fName);
            bmiIntent.putExtra("mi", mi);
            bmiIntent.putExtra("lName", lName);
            bmiIntent.putExtra("gender", gender);
            bmiIntent.putExtra("height", height);
            bmiIntent.putExtra("weight", weight);

            startActivity(bmiIntent);
        });


    }
}