package com.thoughworks.retail.view.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughworks.retail.BR;
import com.thoughworks.retail.R;
import com.thoughworks.retail.model.Product;
import com.thoughworks.retail.viewmodel.ProductDetailViewModel;

/**
 * Display detail about product
 * Created by prateek.aggarwal on 8/18/16.
 */
public class ProductDetailFragment extends MasterRetailFragment {


    private Product product;

    public static ProductDetailFragment newInstance(Product product){

        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.product = product;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail,container,false);
        binding.setVariable(BR.detail,new ProductDetailViewModel(getContext(),
                getActivity().getSupportFragmentManager(),product));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbarTitle("Detail");
    }
}
