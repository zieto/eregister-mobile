package com.umk.register.menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.umk.register.R;
import com.umk.register.grades.GradesActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class GradesListActivity extends AppCompatActivity {

    ListView lista;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        String studentName = sharedPreferences.getString("studentName", "");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Oceny"+" - "+studentName);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_grades_list);
        lista = findViewById(R.id.listView);
        getJSON("http://krzyzunlukas.nazwa.pl/diary-api/api.php");
    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                String action = "subjects_list";
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode(action, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json).append("\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadData(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadData(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        final String[] grade_id = new String[jsonArray.length()];
        final String[] name = new String[jsonArray.length()];
        ArrayList<String> przedmioty = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            grade_id[i] = obj.getString("id");
            name[i] = obj.getString("name");
            przedmioty.add(name[i]);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, przedmioty);
        lista.setAdapter(adapter);
        for (int i=1; i<= jsonArray.length(); i++) {
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent n = new Intent(GradesListActivity.this, GradesActivity.class);
                    n.putExtra("grade_name", name[position]);
                    n.putExtra("grade_id", grade_id[position]);
                    startActivity(n);
                }
            });
        }
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }



}
