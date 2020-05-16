package com.umk.diary.grades;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.content.SharedPreferences;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.umk.diary.R;

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

public class Dane_his extends AppCompatActivity {

    ListView listView;
    TextView srednia, teacher;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final String value = "sid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getJSON("http://10.0.2.2:5050/getdata.php");
        setContentView(R.layout.activity_dane_ang);
        listView = findViewById(R.id.listView);
        srednia = findViewById(R.id.srednia);
        teacher = findViewById(R.id.teacher);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Historia");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }


    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String id = sharedPreferences.getString(value,"");
                String action = "get_grades";
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8");
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
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] oceny = new String[jsonArray.length()];
        String[] desc = new String[jsonArray.length()];
        String[] datetime = new String[jsonArray.length()];
        double suma = 0;
        int imgid[] = new int[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            oceny[i] = obj.getString("angielski");
            desc[i] = obj.getString("desc");
            datetime[i] = obj.getString("datetime");
        }
        for (int i = 0; i < jsonArray.length(); i++){
            String temp = "wystawiono: "+datetime[i];
            datetime[i] = temp;
            suma = suma + Integer.parseInt(oceny[i]);
            if (oceny[i].equals("1")){
                imgid[i] = R.drawable.grade_1;
            }
            else if (oceny[i].equals("2")){
                imgid[i] = R.drawable.grade_2;
            }
            else if (oceny[i].equals("3")){
                imgid[i] = R.drawable.grade_3;
            }
            else if (oceny[i].equals("4")){
                imgid[i] = R.drawable.grade_4;
            }
            else if (oceny[i].equals("5")){
                imgid[i] = R.drawable.grade_5;
            }
            else if (oceny[i].equals("6")){
                imgid[i] = R.drawable.grade_6;
            }
        }
        suma = suma/jsonArray.length();
        CustomListView customListView = new CustomListView(this,desc,datetime,imgid);
        listView.setAdapter(customListView);
        srednia.setText("średnia ocen: "+Double.toString(suma));
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}