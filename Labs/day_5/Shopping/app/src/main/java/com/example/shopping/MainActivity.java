package com.example.shopping;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ImageView imageView ;
    Button btnPRV ;
    Button btnNXT ;
    TextView textTitle ;
    TextView textPrice ;
    TextView textDesc ;
    RatingBar ratingBar ;
    int counter = 0 ;



    Product[] locProducts = new Product[30] ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        btnPRV = findViewById(R.id.btnPRV);
        btnNXT = findViewById(R.id.btnNXT);
        textTitle = findViewById(R.id.textTitle);
        textDesc = findViewById(R.id.textDESC);
        textPrice = findViewById(R.id.textPRICE);
        ratingBar = findViewById(R.id.ratingBar);

        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);


                textTitle.setText(locProducts[0].title);
                textPrice.setText(String.valueOf(locProducts[0].price));
                textDesc.setText(locProducts[0].desc);
                ratingBar.setRating(locProducts[0].Rating.floatValue());


            }
        };

        new Thread(){
            @Override
            public void run() {
                try {
                    String list = GetList();
                    JSONObject jsonObj = new JSONObject(list);
                    JSONArray products = jsonObj.getJSONArray("products");

                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        locProducts[i] = new Product() ;
                        locProducts[i].title = c.getString("title");
                        locProducts[i].desc = c.getString("description");
                        locProducts[i].imgURL = c.getString("thumbnail");
                        locProducts[i].price = c.getDouble("price");
                        locProducts[i].Rating = c.getDouble("rating");

                    }

                    new DownloadTask().execute(locProducts[counter].imgURL);

                    Message msg = new Message();
                    handler.sendMessage(msg);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();



        btnNXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            incremnt();
                textTitle.setText(locProducts[counter].title);
                textPrice.setText(String.valueOf(locProducts[counter].price));
                textDesc.setText(locProducts[counter].desc);
                ratingBar.setRating(locProducts[counter].Rating.floatValue());

                new DownloadTask().execute(locProducts[counter].imgURL);
            }
        });

        btnPRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
                textTitle.setText(locProducts[counter].title);
                textPrice.setText(String.valueOf(locProducts[counter].price));
                textDesc.setText(locProducts[counter].desc);
                ratingBar.setRating(locProducts[counter].Rating.floatValue());

                new DownloadTask().execute(locProducts[counter].imgURL);

            }
        });

    }

    private void incremnt()
    {
        if(counter == 29 )
        {
        }
        else if (counter < 29 && counter > -1){
            counter++ ;

        }
        else {
         counter = 0 ;
        }

    }

    private void decrement()
    {
        if(counter == 0 )
        {
        }
        else if (counter < 29 && counter > -1){
            counter-- ;

        }
        else {
            counter = 28 ;
        }
    }



    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }



    private String GetList() throws IOException {

        String response ;
        URL jsonURL = new URL("https://dummyjson.com/products");
        HttpsURLConnection connection = (HttpsURLConnection) jsonURL.openConnection();

        InputStream is = connection.getInputStream();

        response = convertStreamToString(is);


        return response;
    }

    private Bitmap Download(String url) throws IOException {

        URL imgURL = new URL(url);

        HttpsURLConnection connection  = (HttpsURLConnection) imgURL.openConnection();

        connection.setRequestMethod("GET");

        connection.connect();

        int RespondCode = connection.getResponseCode();

        InputStream is = connection.getInputStream();

        return BitmapFactory.decodeStream(is);
    }

    class DownloadTask extends AsyncTask<String,Integer,Bitmap>
    {


        @Override
        protected Bitmap doInBackground(String... strings) {

            Bitmap bitmap = null ;

            try {
                bitmap = Download(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);

        }
    }

}