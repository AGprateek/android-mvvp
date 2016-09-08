package com.thoughworks.retail.model;

/**
 * Created by prateek.aggarwal on 8/18/16.
 */
public class ProductListSection {

    private int type;
    private Object value;

    public ProductListSection(int type, Object value){

        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
