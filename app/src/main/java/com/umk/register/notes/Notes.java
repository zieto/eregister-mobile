package com.umk.register.notes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.umk.register.R;

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

public class Notes extends AppCompatActivity {

    ListView listView;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final String value = "sid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id","");
        String token = sharedPreferences.getString("token","");
        VerificationNotes verification = new VerificationNotes(this);
        verification.execute("verification",id,token);
        setContentView(R.layout.activity_notes);
//        getJSON("http://10.0.2.2:5050/getnotes.php");
        getJSON("http://krzyzunlukas.nazwa.pl/diary-api/api.php");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Uwagi");
        actionBar.setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.listView);
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
                String action = "get_notes";
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("student_id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8")+"&"
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
                    if(s.contains("brak")){
                        Toast.makeText(getApplicationContext(), "Brak uwag!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        loadIntoListView(s);
                    }
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
        String[] positiv = new String[jsonArray.length()];
        String[] desc = new String[jsonArray.length()];
        String[] datetime = new String[jsonArray.length()];
        String[] teacher = new String[jsonArray.length()];
        String[] tempname = new String[jsonArray.length()];
        String[] tempsurname = new String[jsonArray.length()];
        int imgid[] = new int[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            positiv[i] = obj.getString("positiv");
            desc[i] = obj.getString("text");
            tempname[i] = obj.getString("name");
            tempsurname[i] = obj.getString("surname");
            teacher[i] = (tempname[i]+" "+tempsurname[i]);
            datetime[i] = obj.getString("created_at");
        }
        for (int i = 0; i < jsonArray.length(); i++){
            String temp = "wystawiono: "+datetime[i];
            String temp2 = "przez: " +teacher[i];
            teacher[i] = temp2;
            datetime[i] = temp;
            if (positiv[i].equals("0")){
                imgid[i] = R.drawable.badnote;
            }
            else if (positiv[i].equals("1")){
                imgid[i] = R.drawable.goodnote;
            }
        }
        CustomListViewNotes customListViewNotes = new CustomListViewNotes(this,desc,datetime,teacher,imgid);
        listView.setAdapter(customListViewNotes);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
