package com.kh_kerbabian.saveme.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kh_kerbabian.saveme.R;
import com.kh_kerbabian.saveme.databinding.FragmentDashboardBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private TextView dd;
    private ImageButton forward;
    private ImageButton backward;
    private Calendar currentDate;
    private SimpleDateFormat formateInsance;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDate = Calendar.getInstance();
        formateInsance= new SimpleDateFormat("MMMM, YYYY");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        dd = root.findViewById(R.id.DisplayDate);
        dd.setText(formateInsance.format(currentDate.getTime()));

        forward  = root.findViewById(R.id.action_bar_forward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDate.add(Calendar.MONTH, 1);
                dd.setText(formateInsance.format(currentDate.getTime()));
            }
        });

        backward  = root.findViewById(R.id.action_bar_back);
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDate.add(Calendar.MONTH, -1);
                dd.setText(formateInsance.format(currentDate.getTime()));
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