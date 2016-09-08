package com.thoughworks.retail.binding.configuration;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.thoughworks.retail.BR;

/**
 * Created by prateek.aggarwal on 8/18/16.
 */
public class BaseRecyclerConfiguration extends BaseObservable {


    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private RecyclerView.Adapter adapter;
    private RecyclerView.OnScrollListener mListener;

    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }

    @Bindable
    public RecyclerView.ItemAnimator getItemAnimator() {
        return itemAnimator;
    }

    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        this.itemAnimator = itemAnimator;
        notifyPropertyChanged(BR.itemAnimator);
    }

    @Bindable
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    public void setScrollListener(RecyclerView.OnScrollListener listener){

        this.mListener = listener;
    }

    public RecyclerView.OnScrollListener getScrollListener(){

        return this.mListener;
    }

    @BindingAdapter("bind:configuration")
    public static void configureRecyclerView(RecyclerView recyclerView, BaseRecyclerConfiguration configuration) {
        if(recyclerView.getLayoutManager() == null)
        recyclerView.setLayoutManager(configuration.getLayoutManager());
        recyclerView.setItemAnimator(configuration.getItemAnimator());
        recyclerView.setAdapter(configuration.getAdapter());
        if(configuration.getScrollListener() != null)
        recyclerView.setOnScrollListener(configuration.getScrollListener());
    }
}
