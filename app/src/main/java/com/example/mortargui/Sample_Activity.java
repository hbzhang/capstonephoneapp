package com.example.mortargui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import eu.amirs.JSON;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Sample_Activity extends AppCompatActivity {
    ImageView imageView;
    TextView textview;

    private BackgroundTask.OnDownloadUpdateListener downloadListener;

   // String url = "https://vmihci.herokuapp.com/getimage";

    String url ="http://144.75.191.68:5005/getimage";
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);


        imageView = (ImageView) findViewById(R.id.imageee);
        loadImageFromUrl(url);
        textview = (TextView) findViewById(R.id.texx);


        downloadListener = new BackgroundTask.OnDownloadUpdateListener() {
            @Override
            public void OnDownloadDeckFinish(String Response) {
                textview.setText(Response);
            }
        };

        //BackgroundTask mTask = new BackgroundTask(this.getApplicationContext (), downloadListener);
        //mTask.execute();


        final Button nothreat = findViewById(R.id.nothreatbutt);
        final Intent intent = new Intent(this, Sample_Activity.class);
        nothreat.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0,0);

                //Sample_Activity.super.onResume();


            }
        });

        final Button threat = findViewById(R.id.threatbutton);
        final Intent intent2 = new Intent(this, PhoneActivity.class);

        threat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);

            }
        });



            String url2 = "http://144.75.191.68:5005/getthreat";

            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url2)
                    .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();


                    Sample_Activity.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            //parse myreponse
                            //JSON json = new JSON(myResponse);
                           // String threat = json.key("threats").index(0).key("gunthreat").stringValue();
                            // String threat = json.key("gunthreat").stringValue();
                            //then put to textview
                            textview.setText("Risk: " + myResponse);
                        }
                    });
                }

            }

        });



    }

    private void update() {
        Notification notification = null;
        // update your interface here
        textview.setText(TestService.threat);
        notification.defaults |= Notification.DEFAULT_SOUND;
    }
    


    private void loadImagefromURL_HTTP(String url){

        try{
            //String url = "http://172.22.30.55:8080/data/product_images/"+data.get("col_1");
            URL ulrn = new URL(url);
            HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
            InputStream is = con.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(is);
            if (null != bmp)
                imageView.setImageBitmap(bmp);
            else
                System.out.println("Bitmap is NULL");

            if (con != null) {
                con.disconnect();

            }
        } catch(Exception e) {
            //e.printStackTrace();
            Log.d("debug", e.getMessage());
        }
    }

    private void loadImageFromUrl(String url) {
        Log.d("picasso","message");
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });



    }


}
