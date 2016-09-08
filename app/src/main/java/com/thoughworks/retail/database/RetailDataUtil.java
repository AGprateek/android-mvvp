package com.thoughworks.retail.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.thoughworks.retail.model.CartItem;
import com.thoughworks.retail.model.Product;
import com.thoughworks.retail.model.ProductListSection;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Util class for accessing DatabaseHelper
 * Created by prateek.aggarwal on 8/18/16.
 */
public class RetailDataUtil {

    /**
     * HEADER ITEM TYPE FOR PRODUCT LIST
     * */
    public static final int SECTION_HEADER = 0x01;

    /**
     * ITEM TYPE FOR PRODUCT LIST
     * */
    public static final int SECTION_ITEM = 0x02;

    /**
     * CATEGORY ID FOR ELECTRONICS
     * */
    private final String CATEGORY_ELECTRONICS = "1";

    /**
     * CATEGORY ID FOR FURNITURE
     * */
    private final String CATEGORY_FURNITURE = "2";
    private List<ProductListSection> product = new ArrayList<>();
    private RetailDatabaseHelper mHelper;
    private static RetailDataUtil mSingleton;
    private RetailDataUtil(Context context){

        mHelper = new RetailDatabaseHelper(context);
        initializeProduct();
    }

    public List<ProductListSection> getProduct(){

        return product;
    }



    private void initializeProduct(){


        List<ProductListSection> electronic = getElectronicProduct();
        if(electronic != null && electronic.size() >0){
            product.add(new ProductListSection(SECTION_HEADER,"Electronics"));
            product.addAll(electronic);
        }

        List<ProductListSection> furniture = getFurnitureProduct();
        if(furniture != null && furniture.size() >0){
            product.add(new ProductListSection(SECTION_HEADER,"Furniture"));
            product.addAll(furniture);
        }

    }


    /**
     * Get product for electronic category
     *
     * @return List<ProductListSection> product list for electronic cateogry
     * */
    private List<ProductListSection> getElectronicProduct(){

        return getProduct(CATEGORY_ELECTRONICS);
    }

    /**
     * Get product for furniture category
     * @return List<ProductListSection> product list for particular cateogry
     * */
    private List<ProductListSection> getFurnitureProduct(){

        return getProduct(CATEGORY_FURNITURE);
    }

    /**
     * Add product to cart if not exist else update count of quantity
     * @param productId
     * */
    public void addToCart(String productId){

        SQLiteDatabase db = mHelper.getWritableDatabase();
        String[] projection = {
                ProductItemEntry.PRODUCT_ID,
                CartItemEntry.QUANTITY
        };

        String selection = ProductItemEntry.PRODUCT_ID+"=?";

        Cursor c = db.query(
                CartItemEntry.TABLE_NAME,
                projection,
                selection,
                new String[]{productId},
                null,
                null,
                null
        );
        if(c.moveToFirst()){

            String previous = c.getString(c.getColumnIndex(CartItemEntry.QUANTITY));
            int count = 0;
            if(TextUtils.isEmpty(previous)){
                previous = "0";
            }
            count = Integer.parseInt(previous)+1;
            ContentValues values = new ContentValues();
            values.put(CartItemEntry.PRODUCT_ID,productId);
            values.put(CartItemEntry.QUANTITY,String.valueOf(count));
            db.update(CartItemEntry.TABLE_NAME,values,selection,new String[]{productId});
        }else{
            ContentValues values = new ContentValues();
            values.put(CartItemEntry.PRODUCT_ID,productId);
            values.put(CartItemEntry.QUANTITY,"1");
            db.insert(CartItemEntry.TABLE_NAME,null,values);
        }
    }


    /**
     * Get cart list
     * @return List<CartItem> cart list
     * */
    public List<CartItem> getCartItems(){

        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] projection = {
                ProductItemEntry.PRODUCT_ID,

                CartItemEntry.QUANTITY
        };

        Cursor c = db.query(
                CartItemEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        List<CartItem> product = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                CartItem section = new CartItem(c);
                Product prod = getProductDetail(c.getString(c.getColumnIndex(ProductItemEntry.PRODUCT_ID)));
                section.setId(prod.getId());
                section.setCategoryId(prod.getCategoryId());
                section.setName(prod.getName());
                section.setImageUrl(prod.getImageUrl());
                section.setPrice(prod.getPrice());
                product.add(section);
            }while (c.moveToNext());
        }
        if(!c.isClosed())
            c.close();
        if(db.isOpen())
            db.close();
        return product;
    }

    private Product getProductDetail(String id){

        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] projection = {
                ProductItemEntry.PRODUCT_ID,
                ProductItemEntry.PRODUCT_CATEGORY,
                ProductItemEntry.PRODUCT_NAME,
                ProductItemEntry.PRODUCT_IMAGE,
                ProductItemEntry.PRODUCT_PRICE
        };
        String sortOrder =
                ProductItemEntry.PRODUCT_PRICE + " DESC";
        String selection = ProductItemEntry.PRODUCT_ID+"=?";
        Cursor c = db.query(
                ProductItemEntry.TABLE_NAME,
                projection,
                selection,
                new String[]{id},
                null,
                null,
                sortOrder
        );

        if(c.moveToFirst()){
            return new Product(c);
        }
        return null;
    }

    /**
     * Get product for particular category id
     * @param  category
     * @return List<ProductListSection> product list for particular cateogry
     * */
    private List<ProductListSection> getProduct(String category)
    {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] projection = {
               ProductItemEntry.PRODUCT_ID,
               ProductItemEntry.PRODUCT_CATEGORY,
                ProductItemEntry.PRODUCT_NAME,
                ProductItemEntry.PRODUCT_IMAGE,
                ProductItemEntry.PRODUCT_PRICE
        };
        String sortOrder =
                ProductItemEntry.PRODUCT_PRICE + " DESC";
        String selection = ProductItemEntry.PRODUCT_CATEGORY+"=?";
        Cursor c = db.query(
                ProductItemEntry.TABLE_NAME,
                projection,
                selection,
                new String[]{category},
                null,
                null,
                sortOrder
        );
        List<ProductListSection> product = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                ProductListSection section = new ProductListSection(SECTION_ITEM,new Product(c));
                product.add(section);
            }while (c.moveToNext());
        }
        if(!c.isClosed())
            c.close();
        if(db.isOpen())
            db.close();
        return product;

    }

    /**
     * Delete product from Cart
     * @param  id product id
     *
     * */
    public void deleteItemFromCart(String id){

        SQLiteDatabase database = mHelper.getWritableDatabase();
        String where = ProductItemEntry.PRODUCT_ID+"=?";
        database.delete(CartItemEntry.TABLE_NAME,where,new String[]{id});

    }

    public synchronized static RetailDataUtil getInstance(Context context){

        if(mSingleton == null)
            mSingleton = new RetailDataUtil(context);

        return mSingleton;
    }





}
