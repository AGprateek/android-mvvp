package com.thoughworks.retail.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.thoughworks.retail.R;
import com.thoughworks.retail.view.fragment.MasterRetailFragment;

/**
 * Base activity class handling management of fragment
 * pop back stack and backpress functionolity
 * Created by prateek.aggarwal on 8/18/16.
 */
public class MasterRetailActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {

        if(!onPopBackStack())
                finish();
    }

    protected boolean onPopBackStack(){

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.executePendingTransactions()) {
            return true;
        }

         MasterRetailFragment fragment = (MasterRetailFragment) fragmentManager
                .findFragmentById(R.id.fragment_base_container);

        boolean isEventHandledByFragment = fragment != null
                && fragment.onPopBackStack();

        if (fragmentManager.getBackStackEntryCount() > 1
                && !isEventHandledByFragment) {

            fragmentManager.popBackStack();
            fragmentManager.beginTransaction().remove(fragment).commit();

            return true;
        }

        return isEventHandledByFragment;
    }
}
