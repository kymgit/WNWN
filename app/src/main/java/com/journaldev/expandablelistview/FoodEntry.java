package com.journaldev.expandablelistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Kym on 4/27/2016.
 */
public class FoodEntry extends AppCompatActivity
{
    private String Name ;
    private String Category;
    private int ExpiryDate;
    private int Quantity;
    private String id;



    public FoodEntry(String name, String category, int expiryDate, int quantity) {
        Name = name;
        if (isExpired(expiryDate ))
        {
            Category = "Expired";
        }
        else
        {
            Category = category;
        }

        ExpiryDate = expiryDate;
        Quantity = quantity;

        if (Category.equals("Bread"))
        {
            id ="0";
        }

        else if (Category.equals("Meat"))
        {
            id ="1";
        }
        else if (Category.equals("Vegetable"))
        {
            id ="2";
        }
        else if (Category.equals("Medicine"))
        {
            id ="3";
        }

        else if (Category.equals("Sweets"))
        {
            id ="4";
        }

        else if (Category.equals("Expired"))
        {
            id ="5";
        }

    }

    public String getName() {
        return Name;
    }

    public String getWhole() {
        return Name+Category+String.valueOf(ExpiryDate)+String.valueOf(Quantity);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(int expiryDate) {
        ExpiryDate = expiryDate;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public boolean isExpired(int date)
    {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = df.format(c.getTime());
        int datenow = Integer.parseInt(formattedDate);
        boolean expired = date < datenow ;
        return expired;
    }
   // @Override
    public int compareTo(FoodEntry compareDat) {
        int compareDate= compareDat.getExpiryDate();
        /* For Ascending order*/
        return this.ExpiryDate-compareDate;

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }

    //public void Categorize
}
