package com.umk.register.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.umk.register.menu.MenuActivity;
import com.umk.register.R;
import com.umk.register.menu.settings.SelectStudentActivity;

import java.io.IOException;
import java.security.GeneralSecurityException;


public class LoginActivity extends AppCompatActivity {
    EditText EmailET, PasswordET;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final String value = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString(value,"");
        Boolean studentChecked = sharedPreferences.getBoolean("student_checked",false);
        if (id.length() > 0 && studentChecked) {
            Intent i = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(i);
            finish();
        }
        if (id.length() > 0 && !studentChecked) {
            Intent i = new Intent(LoginActivity.this, SelectStudentActivity.class);
            startActivity(i);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmailET = findViewById(R.id.emailET);
        PasswordET = findViewById(R.id.passwordET);
    }

    public void OnLogin(View view){
        String email = EmailET.getText().toString();
        String password = PasswordET.getText().toString();
        String type = "login";

        Context context = getApplicationContext();
        try {
            MasterKey mainKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            SharedPreferences encryptedSharedPreferences = EncryptedSharedPreferences.create(
                    context,
                    "encryptedData",
                    mainKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            encryptedSharedPreferences
                    .edit()
                    .putString("token",password)
                    .putString("email",email)
                    .apply();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,email,password);

    }

    public void onClick(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://eregister.co.pl"));
        startActivity(browserIntent);
    }





}
