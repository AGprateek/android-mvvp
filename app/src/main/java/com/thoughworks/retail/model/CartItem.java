package com.thoughworks.retail.model;

import android.database.Cursor;

import com.thoughworks.retail.database.CartItemEntry;

/**
 * Created by prateek.aggarwal on 8/18/16.
 */
public class CartItem extends Product {

    private int count;


    public CartItem(Cursor c){

        count = c.getInt(c.getColumnIndex(CartItemEntry.QUANTITY));
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
