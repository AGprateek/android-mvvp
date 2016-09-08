package com.thoughworks.retail.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thoughworks.retail.database.RetailDataUtil;
import com.thoughworks.retail.model.CartItem;
import com.thoughworks.retail.view.fragment.MasterRetailFragment;
import com.thoughworks.retail.view.fragment.ProductDetailFragment;

/**
 * View model for cart item in recycler view , display item detail
 * and can delete item from cart too.
 * Created by prateek.aggarwal on 8/18/16.
 */
public class CartItemViewModel {

    private CartItem item;
    private FragmentManager fragmentManager;
    private DeletedItem mCallback;
    private Context mContext;

    public CartItemViewModel(Context context,FragmentManager manager, CartItem item, DeletedItem callback){

        this.item = item;
        this.fragmentManager = manager;
        mCallback = callback;
        this.mContext  = context;
    }

    public CartItem getItem() {
        return item;
    }

    public void deleteItem(CartItem item){

        RetailDataUtil.getInstance(mContext).deleteItemFromCart(item.getId());
        mCallback.deleted();
    }

    public void showProductDetail(CartItem product){

        ProductDetailFragment fragment = ProductDetailFragment.newInstance(product);
        MasterRetailFragment.addToBackStack(fragmentManager,fragment);
    }

    /**
     * Setter for setting image in  image view
     * any image view having app:image must provide value URL
     * e.g app:image = "www.gf.com/image.jpeg"
     * */
    @BindingAdapter("app:image")
    public static void setImageResource(ImageView view, String url){

        Picasso.with(view.getContext()).load(url).into(view);
    }

    /**
     * Display the price of product
     * */
    public String getPrice() {
        return "Price : Rs."+item.getPrice();
    }

    /**
     * Name of product
     * */
    public String getName() {
        return "Name : "+item.getName();
    }
    /**
     * Display the quantity of product
     * */
    public String getQuantity(){

        return "Quantity :"+item.getCount();
    }

    public interface DeletedItem{

        public void deleted();
    }
}
