package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {


    private static final String PREF_FILE = "PREF_FILE" ;
    private static final String PRIV_FILE = "PRIV_FILE";

    String phone = "";


    TextView txtMobile   ;
    TextView txtMessage  ;

    Button btnBack ;
    Button btnWsh ;
    Button btnRSH ;
    Button btnWIS ;
    Button btnRIS ;

    Button btnWDB ;
    Button btnRDB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtMessage = findViewById(R.id.textMessage);
        txtMobile = findViewById(R.id.textMobile);

        btnBack = findViewById(R.id.btnBack);
        btnWsh = findViewById(R.id.btnWSH);
        btnRSH = findViewById(R.id.btnRSH);
        btnWIS = findViewById(R.id.btnWIS);
        btnRIS = findViewById(R.id.btnRIS);
        btnWDB = findViewById(R.id.btnWDB);
        btnRDB = findViewById(R.id.btnRDB);

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

        btnRSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(SecondActivity.PREF_FILE, Context.MODE_PRIVATE);

                txtMobile.setText(preferences.getString("MOBILE_NUMBER","N/A"));
                txtMessage.setText(preferences.getString("MESSAGE","N/A"));


            }
        });

        btnWsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(SecondActivity.PREF_FILE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("MOBILE_NUMBER",txtMobile.getText().toString());
                editor.putString("MESSAGE",txtMessage.getText().toString());
                editor.commit();

                txtMobile.setText("");
                txtMessage.setText("");

            }
        });

        btnWIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(SecondActivity.PRIV_FILE,Context.MODE_PRIVATE);
                    DataOutputStream dos = new DataOutputStream(fos);
                    dos.writeUTF(txtMessage.getText().toString());
                    dos.writeUTF((txtMobile.getText().toString()));

                    txtMobile.setText("");
                    txtMessage.setText("");
                    dos.close();
                    fos.close();


                } catch (FileNotFoundException e) {
                    Toast.makeText(SecondActivity.this,"File Not Found",Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    Toast.makeText(SecondActivity.this,"IO Error",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnRIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(SecondActivity.PRIV_FILE);
                    DataInputStream dis = new DataInputStream(fis);
                    txtMessage.setText(dis.readUTF());
                    txtMobile.setText(dis.readUTF());
                    dis.close();
                    fis.close();

                } catch (FileNotFoundException e) {
                    Toast.makeText(SecondActivity.this,"File Not Found",Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    Toast.makeText(SecondActivity.this,"IO Error",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnWDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageAdapter adapt = new MessageAdapter(SecondActivity.this);

                String inputMobile = txtMobile.getText().toString();
                String inputMessage = txtMessage.getText().toString();

                adapt.insertMessage(new Message(inputMessage, inputMobile));
                phone = inputMobile; // save for later read

                txtMobile.setText("");
                txtMessage.setText("");
            }
        });

        btnRDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageAdapter adapt = new MessageAdapter(SecondActivity.this);

                if (!phone.isEmpty()) {
                    Message msg = adapt.findMessage(phone);

                    if (msg != null) {
                        txtMessage.setText(msg.getMessage());
                        txtMobile.setText(msg.getMobile());
                    } else {
                        txtMessage.setText("Not found");
                        Toast.makeText(SecondActivity.this, "Unable to read", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SecondActivity.this, "No saved phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}