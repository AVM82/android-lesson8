package org.avm.lesson8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

    public interface FragmentListener {

        boolean onClickContextItem(View fragment);

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        mColor = Utils.getColorRandom();
        view.setBackgroundColor(mColor);
        view.setOnClickListener(this::showBottomMenu);
        return view;
    }

    private void showBottomMenu(View view) {
        View bottomMenuView = getLayoutInflater().inflate(R.layout.bottom_menu, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(bottomMenuView);
        bottomMenuView.setOnClickListener(v -> {
            fragmentListener.onClickContextItem(view);
            bottomSheetDialog.dismiss();
        });
        bottomSheetDialog.show();
    }
}