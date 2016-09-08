package com.thoughworks.retail.viewmodel;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.thoughworks.retail.binding.configuration.CartConfiguration;
/**
 * View model for displaying cart item in recycler view.
 * Created by prateek.aggarwal on 8/18/16.
 */

public class CartListViewModel {


    public CartConfiguration cartConfiguration  = new CartConfiguration();
    public CartListViewModel(Context context, FragmentManager manager){


        cartConfiguration.initialize(context, manager);
    }


}
