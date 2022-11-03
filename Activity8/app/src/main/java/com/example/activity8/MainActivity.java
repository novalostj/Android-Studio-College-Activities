package com.example.activity8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText pName, pPrice, pSearch;


    ListView listView;
    ArrayList<String> itemList;
    ArrayAdapter<String> arrayAdapter;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pName = findViewById(R.id.pName);
        pPrice = findViewById(R.id.pPrice);
        pSearch = findViewById(R.id.pSearch);
        listView = findViewById(R.id.listView);
        save = findViewById(R.id.save);

        itemList = FileHelper.readData(this);
        Collections.sort(itemList);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList);
        listView.setAdapter(arrayAdapter);

        save.setOnClickListener(view -> {

            String value = pName.getText().toString().toUpperCase(Locale.ROOT) + " P" + pPrice.getText().toString();

            itemList.add(value);
            Collections.sort(itemList);
            FileHelper.writeData(itemList, getApplicationContext());
            refresh();

            arrayAdapter.notifyDataSetChanged();
        });

        pSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                arrayAdapter.getFilter().filter(pSearch.getText().toString());
            }
        });

        clickEvent();

    }

    public void refresh(){
        finish();
        startActivity(getIntent());
    }

    public void clickEvent(){
        listView.setOnItemClickListener((adapterView, view, i, l) -> {

            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Delete");
            alert.setMessage("Do you want to remove this item from the list?");
            alert.setCancelable(false);

            alert.setNegativeButton("No", (dialogInterface, i1) -> {
                dialogInterface.cancel();
            });

            alert.setPositiveButton("Yes", (dialogInterface, i12) -> {
                itemList.remove(arrayAdapter.getItem(i));
                FileHelper.writeData(itemList, getApplicationContext());
                refresh();
                arrayAdapter.notifyDataSetChanged();
            });

            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        });
    }

}
