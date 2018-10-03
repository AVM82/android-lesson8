package org.avm.lesson8;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListener {

    private FragmentManager mFragmentManager;
    private MainFragment mFragment1;
    private MainFragment mFragment2;
    private MainFragment mFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        mFragment1 = new MainFragment();
        mFragment1.setFragmentListener(this);
        mFragment2 = new MainFragment();
        mFragment2.setFragmentListener(this);
        mFragment3 = new MainFragment();
        mFragment3.setFragmentListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(!item.isChecked());
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.slide_from_top, R.anim.disappear);
        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        switch (item.getItemId()) {
            case R.id.first_fragment: {
                if (item.isChecked()) {
                    mFragmentTransaction.add(R.id.container1, mFragment1, "1")
                            .addToBackStack("1")
                            .commit();
                } else {
                    mFragmentTransaction.remove(mFragment1).commit();
                }
                break;
            }
            case R.id.second_fragment: {
                if (item.isChecked()) {
                    mFragmentTransaction.add(R.id.container2, mFragment2, "2")
                            .addToBackStack("2")
                            .commit();
                } else {
                    mFragmentTransaction.remove(mFragment2).commit();
                }
                break;
            }
            case R.id.third_fragment: {
                if (item.isChecked()) {
                    mFragmentTransaction.add(R.id.container3, mFragment3, "3")
                            .addToBackStack("3")
                            .commit();
                } else {
                    mFragmentTransaction.remove(mFragment3).commit();
                }
                break;
            }
        }
        return true;
    }

    @Override
    public boolean onClickContextItem(View view) {
        final ArrayList<Integer> colorList = new ArrayList<>(3);
        colorList.add(mFragment1.getColor());
        colorList.add(mFragment2.getColor());
        colorList.add(mFragment3.getColor());
        int newColor = Utils.getColorRandom();
        while (colorList.contains(newColor)) {
            newColor = Utils.getColorRandom();
        }
        view.setBackgroundColor(newColor);
        return true;
    }
}
