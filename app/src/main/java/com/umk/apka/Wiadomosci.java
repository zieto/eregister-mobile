package com.umk.apka;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Wiadomosci extends AppCompatActivity {

    public static final String MyPREFERENCES = "myprefs";
    public static final String value = "sid";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        boolean parent = sharedPreferences.getBoolean("parent",false);
        setContentView(R.layout.activity_wiadomosci);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Wiadomosci.this, NewMessage.class);
                startActivity(i);
            }
        });
        if (parent == false){
            fab.hide();
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Wiadomości");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
