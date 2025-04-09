package com.example.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


    TextView txtURL ;
    Button btnDOWN ;
    ImageView imageView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtURL = findViewById(R.id.txtURL);
        btnDOWN = findViewById(R.id.btnDOWN);
        imageView = findViewById(R.id.imageView);

        btnDOWN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadTask Task =  new DownloadTask();
                Task.execute(txtURL.getText().toString());
            }
        });



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