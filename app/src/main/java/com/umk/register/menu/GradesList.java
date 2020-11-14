package com.umk.register.menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.umk.register.R;
import com.umk.register.grades.Grades;

import java.util.ArrayList;

public class GradesList extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_list);
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
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Matematyka");
                            i.putExtra("grade_id","1");
                            startActivity(i);
                        }
                        else if (position == 1){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Biologia");
                            i.putExtra("grade_id","2");
                            startActivity(i);
                        }
                        else if (position == 2){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Fizyka");
                            i.putExtra("grade_id","3");
                            startActivity(i);
                        }
                        else if (position == 3){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Informatyka");
                            i.putExtra("grade_id","4");
                            startActivity(i);
                        }
                        else if (position == 4){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Geografia");
                            i.putExtra("grade_id","5");
                            startActivity(i);
                        }
                        else if (position == 5){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Przyroda");
                            i.putExtra("grade_id","6");
                            startActivity(i);
                        }
                        else if (position == 6){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Chemia");
                            i.putExtra("grade_id","7");
                            startActivity(i);
                        }
                        else if (position == 7){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Religia");
                            i.putExtra("grade_id","8");
                            startActivity(i);
                        }
                        else if (position == 8){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Wychowanie fizyczne");
                            i.putExtra("grade_id","9");
                            startActivity(i);
                        }
                        else if (position == 9){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Język polski");
                            i.putExtra("grade_id","10");
                            startActivity(i);
                        }
                        else if (position == 10){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Język angielski");
                            i.putExtra("grade_id","11");
                            startActivity(i);
                        }
                        else if (position == 11){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Historia");
                            i.putExtra("grade_id","12");
                            startActivity(i);
                        }
                        else if (position == 12){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Wiedza o społeczeństwie");
                            i.putExtra("grade_id","13");
                            startActivity(i);
                        }
                        else if (position == 13){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Muzyka");
                            i.putExtra("grade_id","14");
                            startActivity(i);
                        }
                        else if (position == 14){
                            Intent i = new Intent(GradesList.this, Grades.class);
                            i.putExtra("grade_name","Plastyka");
                            i.putExtra("grade_id","15");
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
