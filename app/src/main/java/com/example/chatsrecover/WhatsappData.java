package com.example.chatsrecover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class WhatsappData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_data);

        TextView v = (TextView) findViewById(R.id.textView2);
        SharedPreferences sp = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        String name = sp.getString("Name","");
        v.setText(name);
    }
}