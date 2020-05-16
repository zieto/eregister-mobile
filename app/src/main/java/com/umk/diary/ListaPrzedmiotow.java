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
import com.umk.diary.grades.Dane_bio;
import com.umk.diary.grades.Dane_che;
import com.umk.diary.grades.Dane_fiz;
import com.umk.diary.grades.Dane_geo;
import com.umk.diary.grades.Dane_his;
import com.umk.diary.grades.Dane_inf;
import com.umk.diary.grades.Dane_mat;
import com.umk.diary.grades.Dane_muz;
import com.umk.diary.grades.Dane_plas;
import com.umk.diary.grades.Dane_pol;
import com.umk.diary.grades.Dane_prz;
import com.umk.diary.grades.Dane_rel;
import com.umk.diary.grades.Dane_wf;
import com.umk.diary.grades.Dane_wos;

import java.util.ArrayList;

public class ListaPrzedmiotow extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_przedmiotow);
        final ListView lista = findViewById(R.id.listView);

        ArrayList<String> przedmioty = new ArrayList<>();
        przedmioty.add("matematyka");
        przedmioty.add("biologia");
        przedmioty.add("fizyka");
        przedmioty.add("informatyka");
        przedmioty.add("geografia");
        przedmioty.add("przyroda");
        przedmioty.add("chemia");
        przedmioty.add("religia");
        przedmioty.add("wychowanie fizyczne");
        przedmioty.add("polski");
        przedmioty.add("angielski");
        przedmioty.add("historia");
        przedmioty.add("wiedza o społeczeństwie");
        przedmioty.add("muzyka");
        przedmioty.add("plastyka");
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
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_mat.class);
                            startActivity(i);
                        }
                        else if (position == 1){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_bio.class);
                            startActivity(i);
                        }
                        else if (position == 2){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_fiz.class);
                            startActivity(i);
                        }
                        else if (position == 3){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_inf.class);
                            startActivity(i);
                        }
                        else if (position == 4){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_geo.class);
                            startActivity(i);
                        }
                        else if (position == 5){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_prz.class);
                            startActivity(i);
                        }
                        else if (position == 6){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_che.class);
                            startActivity(i);
                        }
                        else if (position == 7){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_rel.class);
                            startActivity(i);
                        }
                        else if (position == 8){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_wf.class);
                            startActivity(i);
                        }
                        else if (position == 9){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_pol.class);
                            startActivity(i);
                        }
                        else if (position == 10){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_ang.class);
                            startActivity(i);
                        }
                        else if (position == 11){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_his.class);
                            startActivity(i);
                        }
                        else if (position == 12){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_wos.class);
                            startActivity(i);
                        }
                        else if (position == 13){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_muz.class);
                            startActivity(i);
                        }
                        else if (position == 14){
                            Intent i = new Intent(ListaPrzedmiotow.this, Dane_plas.class);
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
