package com.umk.diary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.umk.diary.grades.Dane_ang;
import com.umk.diary.grades.Dane_mat;
import com.umk.diary.grades.Dane_pol;

import java.util.ArrayList;

public class ListaPrzedmiotow extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_przedmiotow);
        final ListView lista = findViewById(R.id.listView);

        ArrayList<String> przedmioty = new ArrayList<>();
        przedmioty.add("polski");
        przedmioty.add("angielski");
        przedmioty.add("matematyka");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, przedmioty);
        lista.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Oceny");
        actionBar.setDisplayHomeAsUpEnabled(true);

        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_pol.class);
                            startActivity(i);
                        }
                        else if (position == 1){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_ang.class);
                            startActivity(i);
                        }
                        else if (position == 2){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_mat.class);
                            startActivity(i);
                        }

                    }
                }
        );
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }



}
