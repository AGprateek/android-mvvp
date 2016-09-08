package com.thoughworks.retail.viewmodel;

import android.databinding.BindingAdapter;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thoughworks.retail.model.Product;
import com.thoughworks.retail.view.fragment.MasterRetailFragment;
import com.thoughworks.retail.view.fragment.ProductDetailFragment;

/**
 *
 * Data binding view model for product list item.
 * Created by prateek.aggarwal on 8/18/16.
 */
public class ProductItemViewModel {


    private Product product;
    private FragmentManager fragmentManager;

    public ProductItemViewModel(FragmentManager manager, Product product){

      this.product = product;
        this.fragmentManager = manager;

    }

    public Product getProduct() {
        return product;
    }

    public void showProductDetail(Product product){

        ProductDetailFragment fragment = ProductDetailFragment.newInstance(product);
        MasterRetailFragment.addToBackStack(fragmentManager,fragment);
    }

    /**
     * Display the price of product
     * */
    public String getPrice() {
        return "Rs."+product.getPrice();
    }

    /**
     * Setter method for setting image in layout
     * */
    @BindingAdapter("app:image")
    public static void setImageResource(ImageView view,String url){

        Picasso.with(view.getContext()).load(url).into(view);
    }
}
