package com.example.xmlfile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity2 extends AppCompatActivity {
    EditText firstName,lastName;
    boolean tester=false;
    public void open() {
        @SuppressLint("WrongConstant") FileOutputStream fos = null;
        try {
            fos = openFileOutput("data.xml", 0);
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(fos, "UTF-8");
            serializer.startDocument(null, true);
            //serializer.setFeature();
            serializer.startTag(null, "students");
            serializer.endDocument();
            serializer.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tester=getIntent().getExtras().getBoolean("tester");
        firstName=(EditText) findViewById(R.id.firstName);
        lastName=(EditText) findViewById(R.id.lastName);
        if(!tester){
            open();
            tester=true;
        }


    }

    public void save(View view) {
        try {
            @SuppressLint("WrongConstant") FileOutputStream fos=openFileOutput("data.xml", Context.MODE_APPEND);
            XmlSerializer serializer= Xml.newSerializer();
            serializer.setOutput(fos,"UTF-8");
            //serializer.setFeature();
            serializer.startTag(null,"student");
            serializer.startTag(null,"firstName");
            serializer.text(firstName.getText().toString());
            serializer.endTag(null,"firstName");
            serializer.startTag(null,"lastName");
            serializer.text(lastName.getText().toString());
            serializer.endTag(null,"lastName");
            serializer.endDocument();
            serializer.flush();

            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //finish();
        Intent i=new Intent(this,MainActivity.class);
        Bundle b=new Bundle();
        b.putBoolean("tester",tester);
        i.putExtras(b);
        startActivity(i);
    }
}