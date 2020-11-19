package com.umk.register.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class StudentMeta extends AsyncTask<String, Void, String> {

    Context context;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final String value = "sid";



    public StudentMeta(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];
        String meta_url = "http://krzyzunlukas.nazwa.pl/diary-api/api.php";
        String action = "student_meta";
        sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String user_id = sharedPreferences.getString(value, "");

        if (type.equals("student_meta")) {
            try {
                URL url = new URL(meta_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user_id, "UTF-8")+"&"
                        +URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode(action, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPreExecute();
        try {
            loadData(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void loadData(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] metadata = new String[jsonArray.length()];
        JSONObject obj = jsonArray.getJSONObject(0);
        metadata[0] = obj.getString("name");
        String name = metadata[0];
        metadata[0] = obj.getString("surname");
        String surname = metadata[0];
        String fullName = name+" "+surname;
        SharedPreferences.Editor editor  = sharedPreferences.edit();
        editor.putString("studentName",fullName);
        editor.apply();

    }
}