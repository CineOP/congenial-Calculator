package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button[] numButtons = new Button[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 10; i++) {
            try {
                numButtons[i] = findViewById(R.id.class.getField("butt"+String.valueOf(i)).getInt(null));
            } catch (NoSuchFieldException ignored) {

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

    }


}