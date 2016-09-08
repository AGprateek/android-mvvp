package com.thoughworks.retail.database;

/**
 * Will have detail about Cart table columm name
 * and create statement for Cart table
 * Created by prateek.aggarwal on 8/18/16.
 */
public class CartItemEntry extends ProductItemEntry {

    public static final String QUANTITY = "quantity";
    public static final String TABLE_NAME = "cart";

    protected String getCreateStatement() {
        String query =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        PRODUCT_ID + TEXT_TYPE + COMMA_SEP +
                        QUANTITY + INTEGER_TYPE +
                        " )";

        return query;
    }
}
