package com.thoughworks.retail.database;

/**
 * Will have detail about Product table columm name
 * and create statement for Product
 * Created by prateek.aggarwal on 8/18/16.
 */
public class ProductItemEntry implements BaseEntry {

    public static final String PRODUCT_ID = "id";
    public static final String PRODUCT_CATEGORY = "categoryId";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_IMAGE = "image";
    public static final String PRODUCT_PRICE = "price";
    public static final String TABLE_NAME = "product";


    protected String getCreateStatement() {
        String query =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        PRODUCT_ID + TEXT_TYPE + COMMA_SEP +
                        PRODUCT_CATEGORY + TEXT_TYPE + COMMA_SEP +
                        PRODUCT_NAME+TEXT_TYPE+COMMA_SEP+
                        PRODUCT_IMAGE + TEXT_TYPE + COMMA_SEP +
                        PRODUCT_PRICE + TEXT_TYPE +
                        " )";

        return query;
    }
}
