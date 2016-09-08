package com.thoughworks.retail.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.thoughworks.retail.R;

import java.util.ArrayList;

/**
 * Fragment transaction helper class used for modifying fragment stack
 * Created by prateek.aggarwal on 8/18/16.
 */
public class MasterFragmentTransactionHelper {

    private static final ArrayList<Runnable> capturedEvent = new ArrayList<>();

    // initial true;
    private static boolean mStateSave = true;

    public static void setSaveState(boolean saveState) {
        mStateSave = saveState;
    }

    public static void replace(final FragmentManager fragmentManager,
                               final int targetId, final Fragment fragment,
                               final int enter, final int exit, final int popEnter,
                               final int popExit, final boolean isAddToBackStack) {

        Fragment currentFragment = fragmentManager
                .findFragmentById(R.id.fragment_base_container);


        android.support.v4.app.FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        transaction.setTransition( android.support.v4.app.FragmentTransaction.TRANSIT_NONE);
        if (enter == MasterRetailFragment.NO_ANIMATION
                && exit == MasterRetailFragment.NO_ANIMATION
                && popEnter == MasterRetailFragment.NO_ANIMATION
                && popExit == MasterRetailFragment.NO_ANIMATION) {

        } else {
            transaction.setCustomAnimations(enter, exit, popEnter,
                    popExit);
        }

        try {
            transaction.replace(targetId, fragment,
                    fragment.getClass().getName());
            if (isAddToBackStack) {

                transaction
                        .addToBackStack(fragment.getClass().getName());
            } else {
                if (currentFragment != null) {
                    transaction.remove(currentFragment);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void popBackStackTo(final FragmentManager manager,
                                      final FragmentManager.BackStackEntry entry, final int flag) {


        manager.popBackStack(entry.getId(), flag);
    }

    public static void removeFragmentByTag(final FragmentManager manager,
                                           final String tag) {
        Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment != null) {
            remove(manager, fragment);
        }
    }

    public static void remove(final FragmentManager manager,
                              final Fragment fragment) {
        try {
            manager.beginTransaction().remove(fragment).commit();
        } catch (IllegalStateException ex) {
            manager.beginTransaction().remove(fragment)
                    .commitAllowingStateLoss();
        }
    }

    public static void removeAllFragments(final FragmentManager manager,
                                          final int flag) {

        if (manager != null && manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry backStackEntry = manager
                    .getBackStackEntryAt(0);
            manager.popBackStack(backStackEntry.getId(), flag);
            //manager.popBackStackImmediate();
        }
    }

    private static void executeAction(Runnable runnable) {
        if (mStateSave) {
            capturedEvent.add(runnable);
        } else {
            runnable.run();
        }
    }

    public static void executePendingEvents() {
        if (!mStateSave) {
            while (capturedEvent.size() > 0) {
                capturedEvent.remove(0).run();
            }
        }
    }
}

