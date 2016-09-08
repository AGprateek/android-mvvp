package com.thoughworks.retail.viewmodel;

/**
 * ViewModel class for displaying value of header and footer
 * Created by prateek.aggarwal on 8/18/16.
 */
public class HeaderFooterViewModel {

    private String value;
    public HeaderFooterViewModel(String value){

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
