package com.example.activity10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText inputName, inputPrice;
    RadioButton r_food, r_drink, r_other;

    int currentDBInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.in_pName);
        inputPrice = findViewById(R.id.in_pPrice);

        r_food = findViewById(R.id.r_food);
        r_drink = findViewById(R.id.r_drink);
        r_other = findViewById(R.id.r_other);

        findViewById(R.id.b_add).setOnClickListener(view -> addRecord(inputName.getText().toString(), inputPrice.getText().toString()));

        findViewById(R.id.b_viewF2).setOnClickListener(view -> launchNewActivity(1));
        findViewById(R.id.b_viewD2).setOnClickListener(view -> launchNewActivity(2));
        findViewById(R.id.b_viewO).setOnClickListener(view -> launchNewActivity(3));
        findViewById(R.id.b_viewA).setOnClickListener(view -> launchNewActivity(4));

        r_food.setOnClickListener(view -> currentDBInt = 1);
        r_drink.setOnClickListener(view -> currentDBInt = 2);
        r_other.setOnClickListener(view -> currentDBInt = 3);
    }

    private void launchNewActivity(int i){
        Intent intent = new Intent(getApplicationContext(), displayProducts.class);
        intent.putExtra("dbName", getDatabaseName(i));
        toast(getDatabaseName(i));
        startActivity(intent);
    }

    private void addRecord(String name, String price){

        if (currentDBInt < 1 || name.length() == 0 || price.length() == 0) {
            toast("Missing Field!");
            return;
        }

        try {
            SQLiteDatabase db = openOrCreateDatabase("products", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS tblproduct(id INTEGER PRIMARY KEY AUTOINCREMENT, f_name VARCHAR, f_price VARCHAR, f_type VARCHAR)");

            String mysql = "insert into tblproduct(f_name, f_price, f_type)values(?,?,?)";
            SQLiteStatement statement = db.compileStatement(mysql);
            statement.bindString(1, name.toUpperCase(Locale.ROOT));
            statement.bindString(2, price);
            statement.bindString(3, getDatabaseName(currentDBInt));
            statement.execute();

            inputName.setText("");
            inputPrice.setText("");
            inputName.requestFocus();
            currentDBInt = 0;

            r_food.setChecked(false);
            r_drink.setChecked(false);
            r_other.setChecked(false);

            toast("Successfully added!");
        }
        catch(Exception e){
            toast("Failed to add to DATABASE");
        }
    }

    private String getDatabaseName(int num){
        switch (num){
            case 1:
                return "food";
            case 2:
                return "drink";
            case 3:
                return "other";
            case 4:
                return "all";
            default:
                return null;
        }
    }

    private void toast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }


}