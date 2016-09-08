package com.thoughworks.retail.adapter.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * BaseView holder class for recycler view adapter
 * Created by prateek.aggarwal on 8/18/16.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {


    private ViewDataBinding mBinding;
    public BaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    /**
     * Set data to the binder
     * @param variable Binder Variable Id
     *  @param data  ViewModel class object for the corresponding layout
     * */
    public void setData(int variable, Object data){
        mBinding.setVariable(variable,data);
    }
}
