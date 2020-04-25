package com.umk.apka;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Profil extends AppCompatActivity {

    public static final String MyPREFERENCES = "myprefs";
    public static final String value = "id";
    SharedPreferences sharedPreferences;
    TextView nameTextView, emailTextView, phoneTextView;
    ImageView imageView;
    public String email, name, surname, phone, avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getJSON("http://192.168.0.105:5050/getusermeta.php");
//        getJSON("http://krzyzunlukas.nazwa.pl/diary-api/api.php");
        setContentView(R.layout.activity_profil);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Twój profil");
        actionBar.setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email","");
        imageView = findViewById(R.id.imageView);
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        emailTextView.setText(email);

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
                String action = "get_user_meta";
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    // zamien id na user_id
                    String post_data = URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8")+"&"
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
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
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
        String[] metadata = new String[jsonArray.length()];
        JSONObject obj = jsonArray.getJSONObject(0);
        metadata[0] = obj.getString("name");
        name = metadata[0];
        metadata[0] = obj.getString("surname");
        surname = metadata[0];
        String text;
        text = (name+" "+surname);
        nameTextView.setText(text);
        metadata[0] = obj.getString("phone");
        phoneTextView.setText(metadata[0]);
        metadata[0] = obj.getString("avatar");
        avatar = metadata[0];
        String imgURL  = "http://www.diary.co.pl/upload/"+avatar;
        new DownloadImageTask(imageView).execute(imgURL);

    }

    private class DownloadImageTask extends AsyncTask<String,Void,Bitmap>{
        ImageView imageView;

        public DownloadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){
                e.printStackTrace();
            }
            return logo;
        }

        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
