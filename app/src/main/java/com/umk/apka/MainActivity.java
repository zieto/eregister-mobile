package com.umk.apka;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {
    EditText EmailET, PasswordET;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final String value = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString(value,"");
        if (id.length() > 0) {
            Intent i = new Intent(MainActivity.this,Menu.class);
            startActivity(i);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmailET = (EditText) findViewById(R.id.emailET);
        PasswordET = (EditText) findViewById(R.id.passwordET);
    }

    public void OnLogin(View view){
        String email = EmailET.getText().toString();
        String password = PasswordET.getText().toString();
        String type = "login";
        String String_to_MD5 = password;
        String MD5_Hash_String = md5(String_to_MD5);

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,email,MD5_Hash_String);

    }

    public void onClick(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.0.105:5050/forgotpwd.html"));
        startActivity(browserIntent);
    }


    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }



}
