package com.journaldev.expandablelistview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity

{



    private String MY_FILE_NAME = "masterfoodlist.txt";

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    ArrayList<String> VirtualList = new ArrayList<String>();;
    ArrayList<FoodEntry> FoodList  = new ArrayList<FoodEntry>();;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try
        {
            FileOutputStream fileout=openFileOutput("masterlist.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write("Chicken"+"\t"+"Meat"+"\t"+"20160430"+"\t"+"2");
            outputWriter.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }


        readSavedData();

        parseSavedData();



        /*

        try
        {
            FileOutputStream fileout=openFileOutput("masterlist.txt", MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        */

        ExpandableListDataPump thing = new ExpandableListDataPump(FoodList);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = thing.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }

    public void  readSavedData ( ) {
        StringBuilder datax = new StringBuilder("");
        try {
            FileInputStream fIn = openFileInput ("masterlist.txt" ) ;
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;


            while ( readString != null ) {
                datax.append(readString);
                VirtualList.add(readString);


                readString = buffreader.readLine ( ) ;
            }

            isr.close ( ) ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        //return datax.toString() ;
    }


    public void  parseSavedData ( ) {

        for(int i=0; i<VirtualList.size();i++)
        {
            String[] lines = VirtualList.get(i).split("\t");
            FoodEntry dummy = new FoodEntry(lines[0], lines[1], Integer.parseInt(lines[2]), Integer.parseInt(lines[3]));
            FoodList.add(dummy);
        }

    }

}
