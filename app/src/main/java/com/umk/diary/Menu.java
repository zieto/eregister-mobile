package com.umk.diary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.GridLayout;
import com.umk.diary.schedules.*;

public class Menu extends AppCompatActivity {

    GridLayout gridLayout;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final String value = "id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        gridLayout = findViewById(R.id.mainGrid);
        setSingleEvent(gridLayout);

    }

    private void setSingleEvent(GridLayout gridLayout) {
        for(int i = 0; i<gridLayout.getChildCount();i++){
            CardView cardView=(CardView)gridLayout.getChildAt(i);
            final int finalI= i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0){
                        /*oceny*/
                        Intent i = new Intent(Menu.this, ListaPrzedmiotow.class);
                        startActivity(i);
                    }
                    else if (finalI == 1){
                        /*plan_zajec*/
                        Intent i = new Intent(Menu.this, PlanZajec.class);
                        startActivity(i);
                    }
                    else if (finalI == 2){
                        /*wiadomosci*/
                        Intent i = new Intent(Menu.this, Notes.class);
                        startActivity(i);
                    }
                    else if (finalI == 3){
                        /*profil*/
                        Intent i = new Intent(Menu.this, Profil.class);
                        startActivity(i);
                    }
                    else if (finalI == 4){
                        /*ustawienia*/
                        Intent i = new Intent(Menu.this, SettingsActivity.class);
                        startActivity(i);
                    }
                    else if (finalI == 5){
                        /*wylogowanie*/
                        AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                        builder.setMessage("Czy na pewno chcesz się wylogować?").setPositiveButton("Tak",dialogClickListener).setNegativeButton("Nie",dialogClickListener).show();

                    }

                }
            });
        }
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            sharedPreferences = getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor  = sharedPreferences.edit();
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    Intent i = new Intent(Menu.this,MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("EXIT",true);
                    startActivity(i);
                    editor.clear();
                    editor.apply();
                    finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sharedPreferences2;
        sharedPreferences2 = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        final SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        final boolean pref_auto;
        pref_auto = sharedPreferences.getBoolean("autologin",true);
        new AlertDialog.Builder(this)
                .setMessage("Na pewno chcesz wyjść z aplikacji?")
                .setCancelable(false)
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        if (pref_auto==false){
                            editor2.clear();
                            editor2.apply();
                        }
                    }
                })
                .setNegativeButton("Nie", null)
                .show();
    }


}