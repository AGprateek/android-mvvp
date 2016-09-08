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
import com.thoughworks.retail.viewmodel.CartListViewModel;

/**
 * Display item that are added in cart
 * Created by prateek.aggarwal on 8/18/16.
 */
public class CartFragment extends MasterRetailFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static CartFragment newInstance(){

        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart,container,false);
        binding.setVariable(BR.cartList,new CartListViewModel(getContext(),
                getActivity().getSupportFragmentManager()));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbarTitle("Cart");
    }
}
