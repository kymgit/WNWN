package com.journaldev.expandablelistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity

{
    private String MY_FILE_NAME = "masterfoodlist.txt";

    ExpandableListView expandableListView;
    CustomExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    ArrayList<String> VirtualList = new ArrayList<String>();
    ArrayList<FoodEntry> FoodList = new ArrayList<FoodEntry>();
    HashMap<String, List<String>> expandableListDetail;
    int gp;
    int cp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readSavedData();
        parseSavedData();

        ExpandableListDataPump thing = new ExpandableListDataPump(FoodList);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItem.class);
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
        registerForContextMenu(expandableListView);
    }




    /*context menu(hold press then menu popup) methods */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int groupPosition = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int childPosition = ExpandableListView.getPackedPositionChild(info.packedPosition);

        // Show context menu for groups
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            menu.setHeaderTitle("Child");
            menu.add("Edit");
            menu.add("Delete");
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) item
                .getMenuInfo();

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int groupPosition = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int childPosition = ExpandableListView.getPackedPositionChild(info.packedPosition);

        gp = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        cp = ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD)
        {
            // do someting with child'
            if( item.getTitle().equals("Edit") )
            {
                // Edit the item
                String check = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                for (int i = 0; i < VirtualList.size(); i++)
                {
                    String[] lines = VirtualList.get(i).split("\t");
                    if (check.equals(lines[0]+"\t"+lines[2]+"\t"+"\t"+lines[3]))
                    {
                        Intent editIntent = new Intent(this, EditItem.class);
                        editIntent.putExtra("ItemName",lines[0]);
                        editIntent.putExtra("Category",lines[1]);
                        editIntent.putExtra("ExpDate",lines[2]);
                        editIntent.putExtra("Quantity",lines[3]);
                        startActivityForResult(editIntent,2);
                    }
                }
            }
            else if( item.getTitle().equals("Delete"))
            {
                //Delete
                String check = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                for (int i = 0; i < VirtualList.size(); i++)
                {
                   String[] lines = VirtualList.get(i).split("\t");
                   if (check.equals(lines[0]+"\t"+lines[2]+"\t"+"\t"+lines[3]))
                   {
                       VirtualList.remove(i);

                       //String text = String.format("pask %d %d", i, VirtualList.size());
                       //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                       writeSavedData();
                       VirtualList.clear();
                       readSavedData();
                       parseSavedData();
                       expandableListDetail.get(expandableListTitle.get(groupPosition)).remove(childPosition);
                       expandableListAdapter.setNewItems(expandableListTitle,expandableListDetail);
                   }
                }
            }
        }
        return super.onContextItemSelected(item);
    }





    /* OnActivityResult for accepting intent results and data from AddItem and EditItem Activity*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Check which request it is that we're responding to
        if (requestCode == 1) {

            // Make sure the request was successful
            if (resultCode == 1)
            {
                Toast.makeText(getApplicationContext(), "result accetped", Toast.LENGTH_SHORT).show();
                String ItemName = data.getStringExtra("ItemName");
                String Category = data.getStringExtra("Category");
                Integer ExpDate = data.getIntExtra("ExpDate", 0);
                Integer Quantity = data.getIntExtra("Quantity", 0);

                VirtualList.add(ItemName + "\t" + Category + "\t" + ExpDate + "\t" + Quantity);
                writeSavedData();
                readSavedData();
                parseSavedData();

                expandableListDetail = ExpandableListDataPump.getData();
                expandableListAdapter.setNewItems(expandableListTitle, expandableListDetail);
            }

        }
        else if (requestCode ==2)
        {
            if (resultCode ==2)
            {
                String ItemO = data.getStringExtra("ItemNameO");

                String ExpDateO = data.getStringExtra("ExpDateO");
                String ItemName = data.getStringExtra("ItemName");
                String Category = data.getStringExtra("Category");

                Integer ExpDate = data.getIntExtra("ExpDate2", 0);

                Integer Quantity = data.getIntExtra("Quantity", 0);
                for (int i = 0; i < VirtualList.size(); i++) {
                    String[] lines = VirtualList.get(i).split("\t");
                    if (ItemO.equals(lines[0])&& ExpDateO.equals(lines[2]))
                    {
                        Toast.makeText(getApplicationContext(),VirtualList.get(i), Toast.LENGTH_SHORT).show();
                        VirtualList.remove(i);
                        expandableListDetail.get(expandableListTitle.get(gp)).remove(cp);
                        VirtualList.trimToSize();
                        String line = ItemName + "\t" + Category + "\t" + ExpDate + "\t" + Quantity;
                        VirtualList.add(line);
                        Toast.makeText(getApplicationContext(), line, Toast.LENGTH_SHORT).show();
                        writeSavedData();
                        readSavedData();
                        parseSavedData();
                        expandableListDetail = ExpandableListDataPump.getData();
                        expandableListAdapter.setNewItems(expandableListTitle, expandableListDetail);

                    }
                }
            }
        }
    }


    /*Read,parse, and write functions for data handling from file */
    public void readSavedData()
    {
        StringBuilder datax = new StringBuilder("");
        try {
            FileInputStream fIn = openFileInput("masterlist.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader buffreader = new BufferedReader(isr);

            String readString = buffreader.readLine();

            VirtualList.clear();
            while (readString != null) {
                //
                datax.append(readString);
                VirtualList.add(readString);


                readString = buffreader.readLine();
            }

            isr.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
    public void parseSavedData()
    {
        FoodList.clear();
        for (int i = 0; i < VirtualList.size(); i++) {
            String[] lines = VirtualList.get(i).split("\t");
            FoodEntry dummy = new FoodEntry(lines[0], lines[1], Integer.parseInt(lines[2]), Integer.parseInt(lines[3]));
            FoodList.add(dummy);
        }

        for (int i = 0; i < FoodList.size(); i++) {
            // special comparator for comparing expiry dates
            Collections.sort(FoodList, new Comparator<FoodEntry>() {
                public int compare(FoodEntry food1, FoodEntry food2) {
                    return food1.getExpiryDate() - food2.getExpiryDate();
                }
            });

        }

    }


    public void writeSavedData() {

        try {
            FileOutputStream fileout = openFileOutput("masterlist.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);

            for (int i = 0; i < VirtualList.size(); i++)
            {
                outputWriter.write(VirtualList.get(i)+"\n");
            }

            outputWriter.flush();
            outputWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }






}