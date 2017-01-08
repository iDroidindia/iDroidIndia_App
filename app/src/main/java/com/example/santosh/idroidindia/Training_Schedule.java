package com.example.santosh.idroidindia;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Training_Schedule extends Activity {

    Spinner sp;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    ProgressDialog P_Dialog_TR;
    TextView tv;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training__schedule);
        ShimmerFrameLayout layout = (ShimmerFrameLayout) findViewById(R.id.shimmerlayout);
        layout.startShimmerAnimation();

        list = (ListView)findViewById(R.id.list_schedule);


        String[] date = {"12-12-12","30-23-23"};
        String[] time = {"12:00","2:00"};
        String[] offer = {"Offer1","Offer2"};

        String url = "http://idroidindia.com/apis/idi_apis/get_only_course_info.php";
        AsyncHttpClient client = new AsyncHttpClient();


        custom_adapter ca = new custom_adapter(getApplicationContext(), date, time,offer);
        list.setAdapter(ca);

        tv = (TextView)findViewById(R.id.schedule);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/engraved_gothic.ttf");
        tv.setTypeface(typeFace);

        sp = (Spinner)findViewById(R.id.spinner1);
        arrayList = new ArrayList();

        P_Dialog_TR = new ProgressDialog(Training_Schedule.this);
        P_Dialog_TR.setMessage("Fetching Data...");
        P_Dialog_TR.setCancelable(false);
        P_Dialog_TR.show();
        client.post(url, new JsonHttpResponseHandler() {
            public void onFailure(int statusCode, Throwable e, JSONObject errorResponse) {
                // TODO Auto-generated method stub
                //super.onFailure(statusCode, e, errorResponse);
                System.out.println("Status Code ::: " + statusCode);
                System.out.println("Error Response ::: " + errorResponse);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONArray jsonArray = response.getJSONArray("all_course_information");
                    int json_arr_length = jsonArray.length();
                    System.out.println("the number of line data acces" + json_arr_length);
                   arrayList = new ArrayList<String>();
                    arrayList.add("Select Course For Demo Class");
                    for (int i = 0; i < json_arr_length; i++) {
                        JSONObject JO = jsonArray.getJSONObject(i);
                        String name = JO.getString("course_name");
                        arrayList.add(name);
                    }
                    populate_av(arrayList);

                    sp.setOnItemSelectedListener(new spSpinnerClass1());

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }
    private void populate_av(ArrayList<String> responseList)
    {
        arrayAdapter= new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_textview, responseList);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
       sp.setAdapter(arrayAdapter);
    }
    private class spSpinnerClass1 implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
        {
            String course_name = adapterView.getItemAtPosition(i).toString();
            postWebservice(course_name);

                Toast.makeText(getApplicationContext(),course_name,Toast.LENGTH_SHORT).show();




        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {



        }

        private void postWebservice(String temp)
        {
            RequestParams params = new RequestParams();
            params.put("course_name", temp);
            P_Dialog_TR.dismiss();
        }
    }

}
