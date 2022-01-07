package com.example.xmlfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    boolean tester=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Intent i2=getIntent();*/
        if(getIntent().getExtras()!=null) {
            tester = getIntent().getExtras().getBoolean("tester");
        }
    }

    public void add(View view) {
        Intent i=new Intent(this,MainActivity2.class);
        Bundle b=new Bundle();
        b.putBoolean("tester",tester);
        i.putExtras(b);
        startActivity(i);
    }

    public void display(View view) {
        Intent i=new Intent(this,MainActivity3.class);
        startActivity(i);
    }
}