package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    TextView txtMobile ;
    TextView txtMessage;

    Button btnBack ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtMessage = findViewById(R.id.textMessage);
        txtMobile = findViewById(R.id.textMobile);
        btnBack = findViewById(R.id.btnBack);

        Intent incomingIntent = getIntent();
        String mobile = incomingIntent.getStringExtra("MOBILE_NUMBER");
        String msg = incomingIntent.getStringExtra("MESSAGE");
        txtMessage.setText(msg);
        txtMobile.setText(mobile);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });

    }
}