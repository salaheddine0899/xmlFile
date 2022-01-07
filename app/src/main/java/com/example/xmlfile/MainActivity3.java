package com.example.xmlfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        lv1=(ListView) findViewById(R.id.lv1);
        String s="";
        ArrayList<String>arr=new ArrayList<String>();
        try {
            FileInputStream fis=openFileInput("data.xml");
            XmlPullParser parser= Xml.newPullParser();
            parser.setInput(fis,"UTF-8");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG,null,"students");
            while ((parser.next())!=XmlPullParser.END_DOCUMENT){

                if (parser.getName().equals("firstName")){
                    //parser.require(XmlPullParser.START_TAG,null,"firstName");
                    parser.next();
                    s+=parser.getText();
                    parser.next();
                    parser.require(XmlPullParser.END_TAG,null,"firstName");
                }
                else if (parser.getName().equals("lastName")){
                    //parser.require(XmlPullParser.START_TAG,null,"firstName");
                    parser.next();
                    s+="\t"+parser.getText();
                    parser.next();
                    parser.require(XmlPullParser.END_TAG,null,"lastName");
                    arr.add(s);
                    s="";
                }
            }
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        lv1.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arr));

    }

    public void back(View view) {
        finish();
    }
}