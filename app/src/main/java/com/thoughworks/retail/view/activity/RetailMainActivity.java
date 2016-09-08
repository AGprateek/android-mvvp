package com.thoughworks.retail.view.activity;

import android.os.Bundle;

import com.thoughworks.retail.R;
import com.thoughworks.retail.view.fragment.HomePageFragment;
import com.thoughworks.retail.view.fragment.MasterRetailFragment;


public class RetailMainActivity extends MasterRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_main);

        //Show home fragment
        HomePageFragment fragment = HomePageFragment.newInstance();
        MasterRetailFragment.addToBackStack(this,fragment);
    }
}
