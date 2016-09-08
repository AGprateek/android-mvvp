package com.thoughworks.retail.viewmodel;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.thoughworks.retail.binding.configuration.ProductConifguration;
import com.thoughworks.retail.view.fragment.CartFragment;
import com.thoughworks.retail.view.fragment.MasterRetailFragment;

/**
 *
 * Data binding view model for HomePage fragment will display list of
 * procut and provide navigation to Cart Screen
 * Created by prateek.aggarwal on 8/18/16.
 */
public class ProductListViewModel {


    public ProductConifguration productConifguration = new ProductConifguration();
    private FragmentManager manager;
    public ProductListViewModel(Context context,FragmentManager manager){

        this.manager = manager;

        productConifguration.initialize(context,manager);
    }

    /**
     * Will show cart fragment
     * */
    public void showCart(){

        CartFragment fragment = CartFragment.newInstance();
        MasterRetailFragment.addToBackStack(manager,fragment);

    }


}
