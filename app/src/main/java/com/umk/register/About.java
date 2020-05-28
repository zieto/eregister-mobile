package com.umk.register;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView info;
    TextView info2;
    TextView info3;
    TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("O aplikacji");
        actionBar.setDisplayHomeAsUpEnabled(true);
        info = findViewById(R.id.info);
        info2 = findViewById(R.id.info2);
        info3 = findViewById(R.id.info3);
        link = findViewById(R.id.link);
        info.setText(R.string.info);
        info2.setText(R.string.info2);
        info3.setText(R.string.info3);
    }

    public void onClick(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://icons8.com"));
        startActivity(browserIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
