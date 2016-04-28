package com.journaldev.expandablelistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();



        List<String> Bread = new ArrayList<String>();
        Bread.add("India");
        Bread.add("Pakistan");
        Bread.add("Australia");
        Bread.add("England");
        Bread.add("South Africa");

        List<String> Meat = new ArrayList<String>();
        Meat.add("Brazil");
        Meat.add("Spain");
        Meat.add("Germany");
        Meat.add("Netherlands");
        Meat.add("Italy");

        List<String> Vegetable = new ArrayList<String>();
        Vegetable.add("United States");
        Vegetable.add("Spain");
        Vegetable.add("Argentina");

        Vegetable.add("France");

        Vegetable.add("Crabew");
        Vegetable.add("Russia");

        List<String> Medicine = new ArrayList<String>();
        Medicine.add("India");
        Medicine.add("Pakistan");
        Medicine.add("Australia");
        Medicine.add("England");
        Medicine.add("South Africa");

        expandableListDetail.put("Bread TEAMS", Bread);
        expandableListDetail.put("Meat TEAMS", Meat);
        expandableListDetail.put("Vegetable TEAMS", Vegetable);
        expandableListDetail.put("Medicine TEAMS",Medicine);
        return expandableListDetail;
    }
}
