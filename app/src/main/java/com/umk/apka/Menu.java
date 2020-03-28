package com.umk.apka;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        gridLayout=(GridLayout)findViewById(R.id.mainGrid);

        setSingleEvent(gridLayout);

    }

    // we are setting onClickListener for each element
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
                        Toast.makeText(Menu.this,"Tu będzie plan zajęć", Toast.LENGTH_SHORT).show();
                    }
                    else if (finalI == 2){
                        /*wiadomosci*/
                        Toast.makeText(Menu.this,"Tu będą wiadomości", Toast.LENGTH_SHORT).show();
                    }
                    else if (finalI == 3){
                        /*placeholder*/
                        Toast.makeText(Menu.this,"Tu jeszcze nie wiem co będzie", Toast.LENGTH_SHORT).show();
                    }
                    else if (finalI == 4){
                        /*ustawienia*/
                        Toast.makeText(Menu.this,"Tu będą ustawienia", Toast.LENGTH_SHORT).show();
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
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    Intent i = new Intent(Menu.this,MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("EXIT",true);
                    startActivity(i);
                    finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };


}