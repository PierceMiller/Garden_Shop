package com.plantatree.plantatree;


import android.graphics.drawable.Drawable;

public class Catalogue_Product {

    public String title;
    public Drawable productImage;
    public String description;
    public double price;
    public boolean selected;

    public Catalogue_Product(String title, Drawable productImage, String description, double price) {

        /*Provides the ability to refer to the current object,
        * which can be used to refer current class instance variables*/
        this.title = title;
        this.productImage = productImage;
        this.description = description;
        this.price = price;
    }

}

