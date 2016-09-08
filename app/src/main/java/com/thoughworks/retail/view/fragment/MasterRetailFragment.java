package com.thoughworks.retail.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.thoughworks.retail.R;

/**
 * Created by prateek.aggarwal on 8/18/16.
 */
public class MasterRetailFragment extends Fragment {


    public static int NO_ANIMATION = -1;
    private Toolbar mToolbar;
    public boolean hasNavigation = true;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inflateToolbar();
    }

    /**
     * Fragment replace methods **
     */
    public static void addToBackStack(FragmentActivity activity,
                                      MasterRetailFragment fragment, boolean defaultAnimation) {
        addToBackStack(activity.getSupportFragmentManager(), fragment, defaultAnimation);
    }

    public static void addToBackStack(final FragmentManager manager,
                                      MasterRetailFragment fragment, boolean defaultAnimation) {
        if (defaultAnimation) {
            addToBackStack(manager, fragment);
        } else {
            replace(manager, R.id.fragment_base_container, fragment, 0, 0, 0, 0, true);
        }
    }

    public static void addToBackStack(FragmentActivity activity,
                                      MasterRetailFragment fragment) {
        addToBackStack(activity.getSupportFragmentManager(), fragment);
    }

    public static void addToBackStack(final FragmentManager manager,
                                      MasterRetailFragment fragment) {
        addToBackStack(manager, fragment, R.id.fragment_base_container);
    }

    public static void addToBackStack(final FragmentManager manager,
                                      MasterRetailFragment fragment, int target) {
        replace(manager, target, fragment, NO_ANIMATION, NO_ANIMATION,
                NO_ANIMATION, NO_ANIMATION, true);
    }

    public static void addToBackStack(final FragmentManager manager,
                                      MasterRetailFragment fragment, int enter, int exit) {
        addToBackStack(manager, R.id.fragment_base_container, fragment, enter, exit);
    }

    public static void addToBackStack(final FragmentManager manager,
                                      int targetId, MasterRetailFragment fragment, int enter, int exit) {
        replace(manager, targetId, fragment, enter, exit, 0, 0, true);
    }

    public static void replace(FragmentActivity activity,
                               MasterRetailFragment fragment) {
        replace(activity.getSupportFragmentManager(), R.id.fragment_base_container,
                fragment, NO_ANIMATION, NO_ANIMATION, NO_ANIMATION,
                NO_ANIMATION, true);
    }

    public static void removeTopAndAddToBackStack(FragmentActivity activity,
                                                  MasterRetailFragment fragment) {
        popBackStack(activity.getSupportFragmentManager());
        replace(activity.getSupportFragmentManager(), R.id.fragment_base_container,
                fragment, NO_ANIMATION, NO_ANIMATION, NO_ANIMATION,
                NO_ANIMATION, true);
    }

    public static void replace(FragmentActivity activity, int targetId,
                               MasterRetailFragment fragment) {
        replace(activity.getSupportFragmentManager(), targetId, fragment);
    }

    public static void replace(FragmentManager fragmentManager, int targetId,
                               MasterRetailFragment fragment) {
        // may provide default animation
        replace(fragmentManager, targetId, fragment, NO_ANIMATION,
                NO_ANIMATION, NO_ANIMATION, NO_ANIMATION, false);
    }

    public static void replace(FragmentManager fragmentManager, int targetId,
                               MasterRetailFragment fragment, boolean defaultAnimation) {
        // may provide default animation
        if (defaultAnimation) {
            replace(fragmentManager, targetId, fragment);
        } else {
            replace(fragmentManager, targetId, fragment, 0, 0, 0, 0, false);
        }
    }

    public static void replace(FragmentManager fragmentManager, int targetId,
                               MasterRetailFragment fragment, int enter, int exit, int popEnter,
                               int popExit, boolean isAddToBackStack) {

        MasterFragmentTransactionHelper.replace(fragmentManager, targetId,
                fragment,
                enter, exit, popEnter, popExit, isAddToBackStack);
    }

    public static void popBackStack(FragmentManager manager) {
        if (manager != null) {
            try {

                manager.popBackStack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void isHasNavigation(boolean enable){
        this.hasNavigation = enable;
    }

    private void inflateToolbar() {

        mToolbar = (Toolbar) getView().findViewById(R.id.toolbar);

        if (mToolbar != null) {
            if(hasNavigation)
            mToolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(),
                    R.mipmap.ic_action_navigation_arrow_back, null));
            mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   MasterRetailFragment.popBackStack(getActivity().getSupportFragmentManager());
                }
            });
        }
    }

    public void setToolbarTitle(String title) {
        if (mToolbar != null)
            mToolbar.setTitle(title);
    }

    /***
     * override this and return true if child is handling backpress (Toolbar and
     * device back skipTourButton)
     **/
    public boolean onPopBackStack() {
        return false;
    }

    public static void popBackStackImmediate(FragmentManager manager) {
        if (manager != null) {
            try {
                manager.popBackStackImmediate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void popToHome(FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1) {
            popBackStackTo(fragmentManager,
                    fragmentManager.getBackStackEntryAt(1));
        }
    }

    public static void popBackStackTo(FragmentManager manager,
                                      FragmentManager.BackStackEntry entry) {
        MasterFragmentTransactionHelper.popBackStackTo(manager, entry,
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
