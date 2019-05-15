package com.example.novisync.admin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



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
import java.util.ArrayList;
import java.util.List;



public class FourthActivity extends AppCompatActivity {

    Button add_cst;


    List<GetDataAdapter> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerAdapter recyclerViewadapter;



    String JSON_COMPANYNAME = "companyname";



    String strr = "javadeveloper";
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        //add_cst = (Button) findViewById(R.id.addcstbtn);

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.CustList);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(strr);


    }


    class BackgroundTask extends AsyncTask<String, Void, String> {

        String addurl;

        @Override
        protected void onPreExecute() {

            addurl = "https://rajyamelec99.000webhostapp.com/call_list.php";

            //addurl = "http://192.168.0.130/Routelist.php";

        }

        @Override
        protected String doInBackground(String... args) {
            String job_title;
            job_title = args[0];
            try {
                URL url = new URL(addurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }


            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            //  Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONArray array = jsonObj.getJSONArray("routelist");

                for (int i = 0; i < array.length(); i++) {

                    // JSONObject arrayobj = array.getJSONObject(i);

                    GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

                    JSONObject json = null;
                    try {

                        json = array.getJSONObject(i);


                        GetDataAdapter2.setCall_name(json.getString("call_name"));
                        GetDataAdapter2.setPhnumber(json.getString("phnumber"));



                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                    GetDataAdapter1.add(GetDataAdapter2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            recyclerViewadapter = new RecyclerAdapter(GetDataAdapter1, FourthActivity.this);
            LinearLayoutManager llm=new LinearLayoutManager(getApplicationContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(recyclerViewadapter);
        }

    }


}

