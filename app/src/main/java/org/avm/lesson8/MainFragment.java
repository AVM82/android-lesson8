package org.avm.lesson8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

    public interface FragmentListener {

        boolean onClickContextItem(Fragment fragment);

    }

    private FragmentListener fragmentListener;
    private int mColor;

    public int getColor() {
        return mColor;
    }

    public void setFragmentListener(FragmentListener fragmentListener) {
        this.fragmentListener = fragmentListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frafment, container, false);
        mColor = Utils.getColorRandom();
        view.setBackgroundColor(mColor);
        registerForContextMenu(view);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Click for change color");
        menu.setHeaderIcon(R.drawable.ic_action_name);
        getActivity().getMenuInflater().inflate(R.menu.second_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return fragmentListener.onClickContextItem(this);
    }
}