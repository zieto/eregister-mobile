package com.umk.diary;

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

public class SelectStudent extends AppCompatActivity {

    ListView listView;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final String value = "id";
    public static final String value2 = "sid";
    public static final String value3 = "student_checked";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getJSON("http://10.0.2.2:5050/getstudents.php");
//        getJSON("http://krzyzunlukas.nazwa.pl/diary-api/api.php");
        setContentView(R.layout.activity_select_student);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Wybierz ucznia");
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor  = sharedPreferences.edit();
                        Intent i = new Intent(SelectStudent.this, Menu.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (position == 0){
                            String sid = sharedPreferences.getString("sid0",value2);
                            editor.putString(value2,sid);
                            editor.putBoolean(value3,true);
                            editor.apply();
                            startActivity(i);
                        }
                        else if (position == 1){
                            String sid = sharedPreferences.getString("sid1",value2);
                            editor.putString(value2,sid);
                            editor.putBoolean(value3,true);
                            editor.apply();
                            startActivity(i);
                        }
                        else if (position == 2){
                            String sid = sharedPreferences.getString("sid2",value2);
                            editor.putString(value2,sid);
                            editor.putBoolean(value3,true);
                            editor.apply();
                            startActivity(i);
                        }
                        else if (position == 3){
                            String sid = sharedPreferences.getString("sid3",value2);
                            editor.putString(value2,sid);
                            editor.putBoolean(value3,true);
                            editor.apply();
                            startActivity(i);
                        }
                        else if (position == 4){
                            String sid = sharedPreferences.getString("sid4",value2);
                            editor.putString(value2,sid);
                            editor.putBoolean(value3,true);
                            editor.apply();
                            startActivity(i);
                        }
                        else if (position == 5){
                            String sid = sharedPreferences.getString("sid5",value2);
                            editor.putString(value2,sid);
                            editor.putBoolean(value3,true);
                            editor.apply();
                            startActivity(i);
                        }
                        else if (position == 6){
                            String sid = sharedPreferences.getString("sid6",value2);
                            editor.putString(value2,sid);
                            editor.putBoolean(value3,true);
                            editor.apply();
                            startActivity(i);
                        }
                        else if (position == 7){
                            String sid = sharedPreferences.getString("sid7",value2);
                            editor.putString(value2,sid);
                            editor.putBoolean(value3,true);
                            editor.apply();
                            startActivity(i);
                        }
                        else if (position == 8){
                            String sid = sharedPreferences.getString("sid8",value2);
                            editor.putString(value2,sid);
                            editor.putBoolean(value3,true);
                            editor.apply();
                            startActivity(i);
                        }
                        else if (position == 9){
                            String sid = sharedPreferences.getString("sid9",value2);
                            editor.putString(value2,sid);
                            editor.putBoolean(value3,true);
                            editor.apply();
                            startActivity(i);
                        }

                    }
                }
        );

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
                String action = "get_students";
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8")+"&"
                            +URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode(action, "UTF-8");
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
        sharedPreferences = getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = sharedPreferences.edit();
        JSONArray jsonArray = new JSONArray(json);
        String[] student_id = new String[jsonArray.length()];
        String[] name = new String[jsonArray.length()];
        String[] surname = new String[jsonArray.length()];
        ArrayList<String> students = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String number = Integer.toString(i);
            JSONObject obj = jsonArray.getJSONObject(i);
            student_id[i] = obj.getString("student_id");
            editor.putString("sid"+number,student_id[i]);
            name[i] = obj.getString("name");
            surname[i] = obj.getString("surname");
            students.add(name[i]+" "+surname[i]);
            editor.apply();
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, students);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}