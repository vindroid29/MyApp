package com.vindroid.myapp.ui.sharedelement;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vindroid.myapp.R;

public class SharedElementSecondFragment extends Fragment {

    private SharedElementSecondViewModel mViewModel;

    public static SharedElementSecondFragment newInstance() {
        return new SharedElementSecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shared_element_second_fragment, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.findViewById(R.id.fragment_b_imageView).setTransitionName("simple_fragment_transition");
           // editText.setTransitionName(getString(R.string.activity_mixed_trans));
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SharedElementSecondViewModel.class);
    }

}
