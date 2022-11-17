package com.example.activity10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class editProduct extends AppCompatActivity {

    TextView t_id;
    EditText in_editName, in_editPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        t_id = findViewById(R.id.t_id);

        in_editName = findViewById(R.id.in_editName);
        in_editPrice = findViewById(R.id.in_editPrice);

        t_id.setText(getIntent().getStringExtra("id"));
        in_editName.setText(getIntent().getStringExtra("name"));
        in_editPrice.setText(getIntent().getStringExtra("price"));

        findViewById(R.id.b_delete).setOnClickListener(view -> deleteRecord(t_id.getText().toString()));
        findViewById(R.id.b_edit).setOnClickListener(view -> editRecord(t_id.getText().toString(), in_editName.getText().toString(), in_editPrice.getText().toString()));
    }

    private void editRecord(String id, String name, String price){
        try {
            SQLiteDatabase db = openOrCreateDatabase("products", Context.MODE_PRIVATE, null);

            String mysql = "update tblproduct set f_name = ?, f_price = ? where id=?";
            SQLiteStatement statement = db.compileStatement(mysql);
            statement.bindString(1, name.toUpperCase(Locale.ROOT));
            statement.bindString(2, price);
            statement.bindString(3, id);
            statement.execute();

            toast("Successfully Edited!");
            finish();
            Intent bIntent = new Intent(getApplicationContext(), displayProducts.class);
            startActivity(bIntent);
        }
        catch(Exception e){
            toast("Failed to Edit");
        }
    }

    private void deleteRecord(String id){
        try {

            SQLiteDatabase db = openOrCreateDatabase("products", Context.MODE_PRIVATE, null);

            String mysql = "delete from tblproduct where id=?";
            SQLiteStatement statement = db.compileStatement(mysql);
            statement.bindString(1, id);
            statement.execute();

            toast("Successfully removed!");
            finish();
            Intent bIntent = new Intent(getApplicationContext(), displayProducts.class);
            startActivity(bIntent);

        }
        catch(Exception e){
            toast("Failed to remove");
        }
    }

    private void toast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}