package com.umk.register;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String, Void, Wrapper> {

    Context context;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final String value = "id";
    BackgroundWorker (Context ctx){
        context = ctx;
    }
    @Override
    protected Wrapper doInBackground(String... voids) {
        String type = voids[0];
        String login_url = "http://10.0.2.2:5050/login.php";
//        String login_url = "http://krzyzunlukas.nazwa.pl/diary-api/api.php";
        String action = "login";

        if(type.equals("login")) {
            try {
                String email = voids[1];
                String password = voids[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"
                        +URLEncoder.encode("password", "utf-8")+"="+URLEncoder.encode(password, "UTF-8")+"&"
                        +URLEncoder.encode("action","utf-8")+"="+URLEncoder.encode(action,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                Wrapper w = new Wrapper();
                w.result = bufferedReader.readLine();
                w.role = bufferedReader.readLine();
                w.sid = bufferedReader.readLine();
                w.morethanonestudent = bufferedReader.readLine();
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return w;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Logowanie");
    }

    @Override
    protected void onPostExecute(Wrapper w) {
        super.onPreExecute();
        if(w.result.contains("login not successful") || w.result.contains("<br")) {
            alertDialog.setMessage("Nieprawidłowy e-mail lub hasło użytkownika!");
            alertDialog.show();
        }
        if(!(w.result.contains("login not successful") || w.result.contains("<br")))
        {
            sharedPreferences = context.getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor  = sharedPreferences.edit();
            boolean parent = false;
            if (w.role.contains("1")){
                parent = true;
            }
            editor.putBoolean("parent",parent);
            editor.putString(value,w.result);
            editor.putString("sid",w.sid);
            editor.apply();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Logowanie");
            builder.setMessage("Pomyślnie zalogowano!");
            builder.setPositiveButton("OK",null);
            final AlertDialog dialog = builder.create();
            dialog.show();
            if(w.morethanonestudent.contains("double")){
                final Intent i = new Intent(context, SelectStudent.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                new CountDownTimer(5000, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if(!(dialog.isShowing())){
                            this.cancel();
                            context.startActivity(i);
                        }
                    }

                    @Override
                    public void onFinish() {
                        context.startActivity(i);
                        dialog.dismiss();
                    }
                }.start();
            }
            else {
                final Intent i = new Intent(context, Menu.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                new CountDownTimer(5000, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if(!(dialog.isShowing())){
                            this.cancel();
                            context.startActivity(i);
                        }
                    }

                    @Override
                    public void onFinish() {
                        context.startActivity(i);
                        dialog.dismiss();
                    }
                }.start();
            }

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

class Wrapper{
    String result;
    String role;
    String sid;
    String morethanonestudent;
}
