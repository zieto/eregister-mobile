package com.umk.register.schedules;

import android.content.Context;
import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;

import com.umk.register.R;
import com.umk.register.schedules.ui.main.SectionsPagerAdapter;

public class PlanZajec extends AppCompatActivity {

    public static final String MyPREFERENCES = "myprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id","");
        String token = sharedPreferences.getString("token","");
        VerificationSchedule verification = new VerificationSchedule(this);
        verification.execute("verification",id,token);
        setContentView(R.layout.activity_plan_zajec);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}