package com.thoughworks.retail.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.thoughworks.retail.BR;
import com.thoughworks.retail.R;
import com.thoughworks.retail.adapter.viewholder.BaseViewHolder;
import com.thoughworks.retail.database.RetailDataUtil;
import com.thoughworks.retail.model.Product;
import com.thoughworks.retail.model.ProductListSection;
import com.thoughworks.retail.viewmodel.HeaderFooterViewModel;
import com.thoughworks.retail.viewmodel.ProductItemViewModel;

import java.util.List;

/**
 * Created by prateek.aggarwal on 8/18/16.
 */
public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ProductListSection> mSections;
    private Context mContext;
    private FragmentManager fragmentManager;
    public ProductAdapter(Context context, FragmentManager manager, List<ProductListSection> sections){

        this.mContext = context;
        this.mSections = sections;
        this.fragmentManager = manager;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case RetailDataUtil.SECTION_HEADER:
              return new BaseViewHolder(DataBindingUtil
                        .inflate(LayoutInflater.from(mContext), R.layout.header_item,parent,false));
            case RetailDataUtil.SECTION_ITEM:
                return new BaseViewHolder(DataBindingUtil
                        .inflate(LayoutInflater.from(mContext), R.layout.product_list_item,parent,false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case RetailDataUtil.SECTION_HEADER:
                ((BaseViewHolder)holder).setData(BR.header,new HeaderFooterViewModel(mSections.get(position).getValue().toString()));
                break;
            case RetailDataUtil.SECTION_ITEM:
                ((BaseViewHolder)holder).setData(BR.item,new ProductItemViewModel(fragmentManager,
                        ((Product)mSections.get(position).getValue())));
                break;


        }
    }

    @Override
    public int getItemCount() {
        return this.mSections.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.mSections.get(position).getType();
    }
}
