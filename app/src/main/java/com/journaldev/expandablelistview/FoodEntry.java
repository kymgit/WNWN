package com.journaldev.expandablelistview;

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
        Category = category;
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
}
