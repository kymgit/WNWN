package com.journaldev.expandablelistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {


    private static ArrayList<FoodEntry> item ;

    public ExpandableListDataPump(ArrayList<FoodEntry> item) {
        this.item = item;
    }

    //static
    public static  HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();



        List<String> Bread = new ArrayList<String>();
        List<String> Meat = new ArrayList<String>();
        List<String> Vegetable = new ArrayList<String>();
        List<String> Medicine = new ArrayList<String>();


        for(int i=0; i<item.size();i++)
        {

            // sort by date nlng
            if (item.get(i).getCategory().equals("Bread"))
            {
                Bread.add(item.get(i).getName()+" "+Integer.toString(item.get(i).getExpiryDate()));
            }

            else if (item.get(i).getCategory().equals("Meat"))
            {
                Meat.add(item.get(i).getName()+" "+Integer.toString(item.get(i).getExpiryDate()));
            }
        }




        expandableListDetail.put("Bread", Bread);
        expandableListDetail.put("Meat", Meat);
        expandableListDetail.put("Vegetable", Vegetable);
        expandableListDetail.put("Medicine",Medicine);
        return expandableListDetail;
    }
}
