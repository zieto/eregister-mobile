package com.umk.register.schedules;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class ThursdayFragment extends Fragment {

    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final String value = "sid";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.frag_layout,container,false);
        getJSON("http://krzyzunlukas.nazwa.pl/diary-api/api.php");
        return view;
    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                sharedPreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String id = sharedPreferences.getString(value,"");
                String day = "4";
                String action = "backup_schedule";
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("student_id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8")+"&"
                            +URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode(action, "UTF-8")+"&"
                            +URLEncoder.encode("week_day", "UTF-8")+"="+URLEncoder.encode(day, "UTF-8");
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
                if (s!=null) {
                    try {
                        if (s.contains("brak")) {
                            Toast.makeText(getActivity().getApplicationContext(), "Brak planu zajęć!", Toast.LENGTH_SHORT).show();
                        } else {
                            loadIntoListView(s);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] start_hour = new String[jsonArray.length()];
        String[] start_minute = new String[jsonArray.length()];
        String[] end_hour = new String[jsonArray.length()];
        String[] end_minute = new String[jsonArray.length()];
        String[] full_start = new String[jsonArray.length()];
        String[] full_end = new String[jsonArray.length()];
        String[] subject = new String[jsonArray.length()];
        String[] room = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            start_hour[i] = obj.getString("start_hour");
            start_minute[i] = obj.getString("start_minute");
            end_hour[i] = obj.getString("end_hour");
            end_minute[i] = obj.getString("end_minute");
            if (start_minute[i].equals("5")){
                start_minute[i]="05";
            }
            if (start_minute[i].equals("0")){
                start_minute[i]="00";
            }
            end_hour[i] = obj.getString("end_hour");
            end_minute[i] = obj.getString("end_minute");
            if (end_minute[i].equals("5")){
                end_minute[i]="05";
            }
            if (end_minute[i].equals("0")){
                end_minute[i]="00";
            }
            full_start[i] = (start_hour[i]+":"+start_minute[i]);
            full_end[i] = (end_hour[i]+":"+end_minute[i]);
            subject[i] = obj.getString("name");
            room[i] = obj.getString("classroom_id");
        }

        CustomListViewSchedule customListView = new CustomListViewSchedule(getActivity(),full_start,full_end,subject,room);
        ListView listView = getView().findViewById(R.id.listView);
        listView.setAdapter(customListView);
    }
}