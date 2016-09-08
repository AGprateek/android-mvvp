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
import com.thoughworks.retail.model.CartItem;
import com.thoughworks.retail.viewmodel.CartItemViewModel;
import com.thoughworks.retail.viewmodel.HeaderFooterViewModel;

import java.util.List;

/**
 * Created by prateek.aggarwal on 8/18/16.
 */
public class CartAdapter extends RecyclerView.Adapter<BaseViewHolder> implements CartItemViewModel.DeletedItem {


    private List<CartItem> cartItems;
    private Context mContext;
    private FragmentManager fragmentManager;
    private final int FOOTER =0x01;
    private final int DATA = 0x02;
    private int total;

    public CartAdapter(Context context, FragmentManager manager, List<CartItem> sections){

        this.mContext = context;
        this.cartItems = sections;
        this.fragmentManager = manager;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case DATA:
                return new BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.cart_list_item,parent
                        ,false));

            case FOOTER:
                return new BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.cart_footer,parent,false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case DATA:{
                total += (cartItems.get(position).getCount() *
                        Integer.valueOf(cartItems.get(position).getPrice()));
                holder.setData(BR.cartItem,
                        new CartItemViewModel(mContext,fragmentManager,cartItems.get(position),this));
            }
                break;
            case FOOTER:
                holder.setData(BR.footer,
                        new HeaderFooterViewModel("Total Price : Rs."+total));
                break;
        }

    }

    @Override
    public int getItemCount() {

        if(cartItems.size() > 0)
        return this.cartItems.size()+1;

        return 0;
    }

    @Override
    public int getItemViewType(int position) {

        return position == cartItems.size()? FOOTER:DATA;
    }

    @Override
    public void deleted() {
        notifyDataSetChanged();
    }
}
