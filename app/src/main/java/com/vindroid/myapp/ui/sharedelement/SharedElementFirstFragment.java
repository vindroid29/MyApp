package com.vindroid.myapp.ui.sharedelement;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.vindroid.myapp.R;

public class SharedElementFirstFragment extends Fragment {

    private SharedElementFirstViewModel mViewModel;
    private String TAG;

    public static SharedElementFirstFragment newInstance() {
        return new SharedElementFirstFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.shared_element_first_fragment, container, false);
        final ImageView imageView = (ImageView) view.findViewById(R.id.fragment_a_imageView);

        Button button = (Button) view.findViewById(R.id.btnStart);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                SharedElementSecondFragment simpleFragmentB = SharedElementSecondFragment.newInstance();
                //simpleFragmentB.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.change_image_transform));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setSharedElementReturnTransition(TransitionInflater.from(
                            getActivity()).inflateTransition(R.transition.change_image_trans));
                    setExitTransition(TransitionInflater.from(
                            getActivity()).inflateTransition(android.R.transition.fade));

                    simpleFragmentB.setSharedElementEnterTransition(TransitionInflater.from(
                            getActivity()).inflateTransition(R.transition.change_image_trans));
                    simpleFragmentB.setEnterTransition(TransitionInflater.from(
                            getActivity()).inflateTransition(android.R.transition.fade));
                }
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(imageView, ViewCompat.getTransitionName(imageView))
                        .addToBackStack(TAG)
                        .replace(R.id.container, simpleFragmentB)
                        .commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SharedElementFirstViewModel.class);
    }

}
