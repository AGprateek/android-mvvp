package com.thoughworks.retail.model;

import android.database.Cursor;

import com.thoughworks.retail.database.ProductItemEntry;

/**
 * Created by prateek.aggarwal on 8/18/16.
 */
public class Product {

    private String id;
    private String categoryId;
    private String name;
    private String imageUrl;
    private String price;

    public Product(String[] values) {

        this.id = values[0];
        this.setCategoryId(values[1]);
        this.name = values[2];
        this.imageUrl = values[3];
        this.price = values[4];
    }

    public Product(){}

    public Product(Cursor c){

        this.id = c.getString(c.getColumnIndex(ProductItemEntry.PRODUCT_ID));
        this.categoryId = c.getString(c.getColumnIndex(ProductItemEntry.PRODUCT_CATEGORY));
        this.name = c.getString(c.getColumnIndex(ProductItemEntry.PRODUCT_NAME));
        this.imageUrl = c.getString(c.getColumnIndex(ProductItemEntry.PRODUCT_IMAGE));
        this.price= c.getString(c.getColumnIndex(ProductItemEntry.PRODUCT_PRICE));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
