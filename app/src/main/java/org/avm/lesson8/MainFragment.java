package org.avm.lesson8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

    public interface FragmentListener {

        void onClickBottomMenuItem(MainFragment fragment);
    }

    private final String BACKGROUND_COLOR = "backgroundColor";
    private FragmentListener fragmentListener;
    private int mColor;
    private View mView;

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
        mView.setBackgroundColor(mColor);
    }

    public void setFragmentListener(@NonNull FragmentListener fragmentListener) {
        this.fragmentListener = fragmentListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("[FRAGMENT]", "onCreateView");
        mView = inflater.inflate(R.layout.fragment, container, false);
        mView.setOnClickListener(this::showBottomMenu);
        return mView;
    }

    private void showBottomMenu(@NonNull View view) {
        View bottomMenuView = getLayoutInflater().inflate(R.layout.bottom_menu, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(bottomMenuView);
        bottomMenuView.setOnClickListener(v -> {
            fragmentListener.onClickBottomMenuItem(this);
            bottomSheetDialog.dismiss();
        });
        bottomSheetDialog.show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("[FRAGMENT]", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt(BACKGROUND_COLOR, getColor());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("[FRAGMENT]", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mColor = savedInstanceState.getInt(BACKGROUND_COLOR, Utils.getColorRandom());
        } else {
            mColor = Utils.getColorRandom();
        }
        setColor(mColor);
    }
}