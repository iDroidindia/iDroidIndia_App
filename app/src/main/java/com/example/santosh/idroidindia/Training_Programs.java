package com.example.santosh.idroidindia;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;

import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Training_Programs extends Activity{
    ProgressDialog PD;
    JSONArray JA;
    JSONObject JO;
    String[] Course_Name = new String[20];
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String[] sub_course1 = new String[20];

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training__programs);
        ShimmerFrameLayout layout = (ShimmerFrameLayout) findViewById(R.id.shimmerlayout);
        layout.startShimmerAnimation();

tv = (TextView)findViewById(R.id.training);




        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/engraved_gothic.ttf");
        tv.setTypeface(typeFace);

        String url = "http://idroidindia.com/apis/idi_apis/get_course_categories.php";
        String url2 = "http://idroidindia.com/apis/idi_apis/get_only_course_info.php";
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.list);

        PD = new ProgressDialog(Training_Programs.this);
        PD.setMessage("Fetching Data.....");
        PD.show();

        AsyncHttpClient AC = new AsyncHttpClient();
        AC.post(url, new JsonHttpResponseHandler(){


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                listDataHeader = new ArrayList<String>();
                listDataChild = new HashMap<String, List<String>>();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild);
                expListView.setAdapter(listAdapter);


                PD.dismiss();
                try {

                    JA = response.getJSONArray("idroid_course_category_info");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i =0; i<JA.length(); i++){

                    try {
                        JO = JA.getJSONObject(i);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    try {

                        Course_Name[i] = JO.getString("course_category_name");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    listDataHeader.add(Course_Name[i]);

                }

                List<String>one = new ArrayList<String>();

                one.add("Android Apps Development");
                one.add("Android Apps Dev. with Java");
                one.add("Advance Android Apps Development");
                one.add("iOS Apps Development");
                one.add("Advanced iOS Apps Development");



                List<String> two = new ArrayList<String>();
                two.add("JQuery");
                two.add("HTML5 and CSS3");
                two.add("HTML5 with JavaScript");
                two.add("Bootstrap with AJAX");


                List<String> three = new ArrayList<String>();
                three.add("HTML5 and CSS3");
                three.add("PHP with MySQL");
                three.add("AJAX");
                three.add("JQuery");


                List<String> four = new ArrayList<String>();
                four.add("Joomla Content Management System");
                four.add("WordPress Content Management System");
                four.add("Drupal Content management System");


                List<String> five = new ArrayList<String>();
                five.add("PHP & MySQL Development");
                five.add("Adv. PHP Development");
                five.add("PHP Development with JavaScript");
                five.add("PHP Development with AJAX");

                List<String> six = new ArrayList<String>();
                six.add("HTML5 and CSS3");
                six.add("JavaScript");
                six.add("Bootstrap");

                listDataChild.put(listDataHeader.get(0), one); // Header, Child data
                listDataChild.put(listDataHeader.get(1), two);
                listDataChild.put(listDataHeader.get(2), three);
                listDataChild.put(listDataHeader.get(3), four);
                listDataChild.put(listDataHeader.get(4), five);
                listDataChild.put(listDataHeader.get(5), six);



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);


            }
        });



    }









}
