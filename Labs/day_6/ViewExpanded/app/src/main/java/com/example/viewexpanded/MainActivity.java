package com.example.viewexpanded;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


    Product[] locProducts = new Product[30] ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager manger = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(manger);
                manger.setOrientation(RecyclerView.HORIZONTAL);
                ProductAdapter adapter = new ProductAdapter(MainActivity.this,locProducts);
                recyclerView.setAdapter(adapter);


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



                    Message msg = new Message();
                    handler.sendMessage(msg);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();




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



}


