package com.kerbabian.timero.ui.Statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kerbabian.timero.databinding.FragmentStatisticsBinding;
import com.kerbabian.timero.ui.Home.HomeViewModel;

public class StatsFragment extends Fragment {

    private FragmentStatisticsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StatsViewModel statsViewModel =
                new ViewModelProvider(this).get(StatsViewModel.class);

        binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        statsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);



        final Button test = binding.buttontest;
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel home = new HomeViewModel();
                home.settText("aaaa");
            }
        });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}