package com.journaldev.expandablelistview;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Kym on 4/27/2016.
 */
public class FoodEntry
{
    private String Name ;
    private String Category;
    private int ExpiryDate;
    private int Quantity;

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
    }

    public String getName() {
        return Name;
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
        int compareDate=((FoodEntry)compareDat).getExpiryDate();
        /* For Ascending order*/
        return this.ExpiryDate-compareDate;

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }

    //public void Categorize
}
