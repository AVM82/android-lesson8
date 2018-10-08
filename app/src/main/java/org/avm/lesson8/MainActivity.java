package org.avm.lesson8;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListener {

    private final String ITEM_1 = "0";
    private final String ITEM_2 = "1";
    private final String ITEM_3 = "2";
    private final String CHECK_MENU = "checkMenu";
    private final String TAG = "[ACTIVITY]";
    private final int FRAGMENTS_COUNT = 3;

    private FragmentManager mFragmentManager;
    private boolean[] checkMenu = new boolean[]{false, false, false};
    private MainFragment mFragment1;
    private MainFragment mFragment2;
    private MainFragment mFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mFragment1 = new MainFragment();
            mFragment2 = new MainFragment();
            mFragment3 = new MainFragment();
            mFragment1.setFragmentListener(this);
            mFragment2.setFragmentListener(this);
            mFragment3.setFragmentListener(this);
        } else {
            ArrayList<MainFragment> fragmentsList =
                    (ArrayList) getLastCustomNonConfigurationInstance();
            if (fragmentsList != null) {
                mFragment1 = fragmentsList.get(Integer.parseInt(ITEM_1));
                mFragment2 = fragmentsList.get(Integer.parseInt(ITEM_2));
                mFragment3 = fragmentsList.get(Integer.parseInt(ITEM_3));
            } else {
                Log.d(TAG, "Fragments list equals null after rotate screen");
            }
        }
        Log.d(TAG, "onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.first_fragment).setChecked(checkMenu[Integer.parseInt(ITEM_1)]);
        menu.findItem(R.id.second_fragment).setChecked(checkMenu[Integer.parseInt(ITEM_2)]);
        menu.findItem(R.id.third_fragment).setChecked(checkMenu[Integer.parseInt(ITEM_3)]);
        Log.d(TAG, "onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.slide_from_top, R.anim.disappear);
        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        switch (item.getItemId()) {
            case R.id.first_fragment: {
                if (!item.isChecked()) {
                    mFragmentTransaction.add(R.id.container1, mFragment1, ITEM_1).commit();
                } else {
                    mFragmentTransaction.remove(mFragment1).commit();
                }
                checkMenu[Integer.parseInt(ITEM_1)] = !item.isChecked();
                break;
            }
            case R.id.second_fragment: {
                if (!item.isChecked()) {
                    mFragmentTransaction.add(R.id.container2, mFragment2, ITEM_2).commit();
                } else {
                    mFragmentTransaction.remove(mFragment2).commit();
                }
                checkMenu[Integer.parseInt(ITEM_2)] = !item.isChecked();
                break;
            }
            case R.id.third_fragment: {
                if (!item.isChecked()) {
                    mFragmentTransaction.add(R.id.container3, mFragment3, ITEM_3).commit();
                } else {
                    mFragmentTransaction.remove(mFragment3).commit();
                }
                checkMenu[Integer.parseInt(ITEM_3)] = !item.isChecked();
                break;
            }
        }
        item.setChecked(!item.isChecked());
        return true;
    }

    @Override
    public void onClickBottomMenuItem(MainFragment fragment) {
        final ArrayList<Integer> colorList = new ArrayList<>(FRAGMENTS_COUNT);
        colorList.add(mFragment1.getColor());
        colorList.add(mFragment2.getColor());
        colorList.add(mFragment3.getColor());
        int newColor = Utils.getColorRandom();
        while (colorList.contains(newColor)) {
            newColor = Utils.getColorRandom();
        }
        fragment.setColor(newColor);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBooleanArray(CHECK_MENU, checkMenu);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        checkMenu = savedInstanceState.getBooleanArray(CHECK_MENU);
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Log.d(TAG, "The onRetainCustomNonConfigurationInstance() handler was called");
        ArrayList<MainFragment> fragments = new ArrayList<>(FRAGMENTS_COUNT);
        fragments.add(mFragment1);
        fragments.add(mFragment2);
        fragments.add(mFragment3);
        return fragments;
    }
}
