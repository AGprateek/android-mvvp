package com.thoughworks.retail.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.thoughworks.retail.model.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * RetailDatabaseHelper for creating retail db and adding the predefined product
 * in database.
 * Created by prateek.aggarwal on 8/18/16.
 */
public class RetailDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RetailData.db";
    private Context mContext;

    public RetailDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(new ProductItemEntry().getCreateStatement());
        sqLiteDatabase.execSQL(new CartItemEntry().getCreateStatement());
        updateProdcut(sqLiteDatabase);
    }

    /**
     * Parse the CSV file and add the parsed product to Database
     *
     * @param db reference of SQLiteDatabase
     * */
    private void updateProdcut(SQLiteDatabase db){

        try{

            BufferedReader reader = getAssetBufferedReader();
            if(reader != null ){

                String line = null;
                while(!TextUtils.isEmpty(line = reader.readLine())){
                    String[] values =  line.split(",");

                    if(values != null && values.length == 5){

                        Product product = new Product(values);
                        insertProduct(db,product);
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
    * add the parsed data from CSV file to Database
    * @param db SQLiteDatabase
    * @param product instance of Product having parsed value from CSV
    * */
    private void insertProduct(SQLiteDatabase db,Product product){

        ContentValues values = new ContentValues();
        values.put(ProductItemEntry.PRODUCT_ID,product.getId());
        values.put(ProductItemEntry.PRODUCT_CATEGORY, product.getCategoryId());
        values.put(ProductItemEntry.PRODUCT_NAME, product.getName());
        values.put(ProductItemEntry.PRODUCT_IMAGE, product.getImageUrl());
        values.put(ProductItemEntry.PRODUCT_PRICE, product.getPrice());

        String insertStatement = "insert into "+ProductItemEntry.TABLE_NAME +"("+
                ProductItemEntry.PRODUCT_ID+ProductItemEntry.COMMA_SEP
                +ProductItemEntry.PRODUCT_CATEGORY +ProductItemEntry.COMMA_SEP+
                ProductItemEntry.PRODUCT_NAME+ProductItemEntry.COMMA_SEP+
                ProductItemEntry.PRODUCT_IMAGE+ProductItemEntry.COMMA_SEP+
                ProductItemEntry.PRODUCT_PRICE+
                ") values("+" '"+product.getId()+"'"+ProductItemEntry.COMMA_SEP
                +"'"+product.getCategoryId()+"'"+ProductItemEntry.COMMA_SEP
                +"'"+product.getName()+"'"+ProductItemEntry.COMMA_SEP
                +"'"+product.getImageUrl()+"'"+ProductItemEntry.COMMA_SEP
                +"'"+product.getPrice()+"' )";



        db.execSQL(insertStatement);
    }

    /**
     * @return BufferedReader for CSV file in assetdirectory
     * */
    private BufferedReader getAssetBufferedReader() throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(mContext.getAssets().open("retail_data.csv")));

        return reader;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
