package com.thoughworks.retail.binding.configuration;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.thoughworks.retail.adapter.ProductAdapter;
import com.thoughworks.retail.database.RetailDataUtil;

/**
 * Created by prateek.aggarwal on 8/18/16.
 */
public class ProductConifguration extends BaseRecyclerConfiguration {

    private ProductAdapter mAdapter;

    /**
     * Initialize product recyclerview
     * @param context Application context
     * @param   manager instance of fragment manager
     * */
    public  void initialize(Context context,FragmentManager manager){


        setLayoutManager(new LinearLayoutManager(context));
        setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ProductAdapter(context,manager, RetailDataUtil.getInstance(context).getProduct());
        setAdapter(mAdapter);
    }
}
