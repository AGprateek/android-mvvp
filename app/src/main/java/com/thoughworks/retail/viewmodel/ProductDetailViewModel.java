package com.thoughworks.retail.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thoughworks.retail.database.RetailDataUtil;
import com.thoughworks.retail.model.Product;
import com.thoughworks.retail.view.fragment.CartFragment;
import com.thoughworks.retail.view.fragment.MasterRetailFragment;

/**
 *
 * ViewModel class for ProductDetailFragment
 * Created by prateek.aggarwal on 8/18/16.
 */
public class ProductDetailViewModel {


    private Product product;
    private Context mContext;
    private FragmentManager manager;
    public ProductDetailViewModel(Context context, FragmentManager manager, Product product){

        this.product = product;
        this.mContext = context;
        this.manager = manager;
    }

    public Product getProduct() {
        return product;
    }

    public void addToCart(String id){

        RetailDataUtil.getInstance(mContext).addToCart(id);
    }

    public void showCart(){

        CartFragment fragment = CartFragment.newInstance();
        MasterRetailFragment.addToBackStack(manager,fragment);
    }

    /**
     * Display the price of product
     * */
    public String getPrice() {
        return "Rs."+product.getPrice();
    }



    /**
     * Setter for setting image in  image view
     * any image view having app:image must provide value URL
     * e.g app:image = "www.gf.com/image.jpeg"
     * */
    @BindingAdapter("bind:image")
    public static void loadImage(ImageView view, String url){

        Picasso.with(view.getContext()).load(url).into(view);

    }
}
