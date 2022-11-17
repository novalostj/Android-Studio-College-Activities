package com.example.activity10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class displayProducts extends AppCompatActivity {

    ListView listView;
    ArrayList<String> prodList = new ArrayList<>();
    ArrayAdapter prodListAdapter;
    ArrayList<product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);

        listView = findViewById(R.id.lv_list);

        String dbName = getIntent().getStringExtra("dbName");
        prodList.clear();
        products.clear();

        switch (dbName) {
            case "all":
                getArrayFromDB("food");
                getArrayFromDB("drink");
                getArrayFromDB("other");
                break;
            default:
                getArrayFromDB(dbName);
                break;
        }

        Collections.sort(prodList);

        prodListAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, prodList);
        listView.setAdapter(prodListAdapter);

        prodListAdapter.notifyDataSetChanged();
        listView.invalidateViews();

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            product prod = products.get(i);

            Intent intent = new Intent(getApplicationContext(), editProduct.class);
            intent.putExtra("id", prod.id);
            intent.putExtra("name", prod.name);
            intent.putExtra("price", prod.price);
            startActivity(intent);
        });
    }

    private void getArrayFromDB(String dbName){
        try{
            SQLiteDatabase db = openOrCreateDatabase("products", Context.MODE_PRIVATE, null);
            final Cursor tmpTable = db.rawQuery("Select * from tblproduct", null);

            int id = tmpTable.getColumnIndex("id");
            int name = tmpTable.getColumnIndex("f_name");
            int price = tmpTable.getColumnIndex("f_price");
            int type = tmpTable.getColumnIndex("f_type");

            if (tmpTable.moveToFirst()) do
            {
                product prod = new product(
                        tmpTable.getString(id),
                        tmpTable.getString(name),
                        tmpTable.getString(price));

                if (tmpTable.getString(type).equals(dbName))
                {
                    products.add(prod);
                    prodList.add(tmpTable.getString(name) + " P" + tmpTable.getString(price));
                }
            }
            while (tmpTable.moveToNext());

        }
        catch(Exception e){
            toast("No DB Found ON " + dbName);
        }
    }

    private void toast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}