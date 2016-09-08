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
import com.thoughworks.retail.viewmodel.ProductListViewModel;

/**
 * View displaying list of product available
 * Created by prateek.aggarwal on 8/18/16.
 */
public class HomePageFragment extends MasterRetailFragment {


    /**
    * Return instance of HomePageFragment
    * @return HomePageFragment
    * */
    public static  HomePageFragment newInstance(){

        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isHasNavigation(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Get the binding of layout
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);

        //Attach view model to the binding
        binding.setVariable(BR.productlist,new ProductListViewModel(getContext(),
                getActivity().getSupportFragmentManager()));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbarTitle("Home");
    }
}
