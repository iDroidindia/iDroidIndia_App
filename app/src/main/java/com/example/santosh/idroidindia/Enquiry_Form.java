package com.example.santosh.idroidindia;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Enquiry_Form extends Activity {

    Button send;
    ProgressDialog PD;
    EditText name, email, phone, query;

    int REQUEST_CODE = 83;
    File F_Destination;



    String Str_Image_Path;



TextView tv;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry__form);
        ShimmerFrameLayout layout = (ShimmerFrameLayout) findViewById(R.id.shimmerlayout);
        layout.startShimmerAnimation();

        tv = (TextView)findViewById(R.id.enquiry);

        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/engraved_gothic.ttf");
        tv.setTypeface(typeFace);
        send = (Button) findViewById(R.id.send);

        iv = (ImageView)findViewById(R.id.image);


        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        query = (EditText) findViewById(R.id.query);

        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        String name_img = sdf.format(new Date());

        F_Destination = new File(Environment.getExternalStorageDirectory(), name_img + ".jpg");
        Intent i;
        i = new Intent("android.media.action.IMAGE_CAPTURE");
        i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(F_Destination));
        startActivityForResult(i, REQUEST_CODE);







        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String get_name = String.valueOf(name.getText().toString());
                final String get_email = String.valueOf(email.getText().toString());
                String get_phone = String.valueOf(phone.getText().toString());
                String get_query = String.valueOf(query.getText().toString());

                if (get_name.toString().equals("")) {

                    name.setError("Enter Your Name");

                } else if (get_email.toString().equals("")) {
                    email.setError("Enter Your Name");
                } else if (get_phone.toString().equals("")) {
                    phone.setError("Enter Your Name");
                } else if (get_query.toString().equals("")) {
                    query.setError("Enter Your Name");
                } else {


                    System.out.println("Query");

                    String url = "http://idroidindia.com/apis/new_data.php";

                    RequestParams params = new RequestParams();
                    params.put("name", get_name);
                    params.put("mobile_no", get_phone);
                    params.put("email_id", get_email);
                    params.put("query", get_query);
                    params.put("course","test");
                    try
                    {
                        params.put("Image",F_Destination);
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }

                    AsyncHttpClient client = new AsyncHttpClient();
                    client.post(url, new JsonHttpResponseHandler() {






                        @Override
                        public void onSuccess(int statusCode,
                                              org.apache.http.Header[] headers, JSONObject response) {

                            System.out.println("Success Response ::: "+response);


                            send.setText("Sending...");
                            PD = new ProgressDialog(Enquiry_Form.this);
                            PD.setMessage("Sending Data.....");
                            PD.show();





                            // TODO Auto-generated method stub
                            super.onSuccess(statusCode, headers, response);

                            System.out.println("Success Response ::: " + response);


                            Toast.makeText(getApplicationContext(),get_email,Toast.LENGTH_SHORT).show();



                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                        }
                    });



                }

                PD.dismiss();
                send.setText("Send Away!");
                name.setText("");
                email.setText("");
                phone.setText("");
                query.setText("");
            }
        });



    }


    protected void onActivityResult(int requestCode, int resultCode, Intent I_CAM_B) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK ) {

            FileInputStream in;

            try {
                in = new FileInputStream(F_Destination);

                BitmapFactory.Options B_Options = new BitmapFactory.Options();
                B_Options.inSampleSize = 10;
                Str_Image_Path = F_Destination.getAbsolutePath();


                Toast.makeText(getApplicationContext(),Str_Image_Path,Toast.LENGTH_SHORT).show();
                Bitmap BMP_File = BitmapFactory.decodeStream(in, null, B_Options );

                iv.setImageBitmap(BMP_File);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        else
        {
            Toast.makeText(getApplicationContext(),"There is some issue while clicking the Photo.",Toast.LENGTH_SHORT).show();

        }

    }

}
