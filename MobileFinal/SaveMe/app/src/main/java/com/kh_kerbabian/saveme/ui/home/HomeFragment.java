package com.kh_kerbabian.saveme.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



import com.google.android.material.snackbar.Snackbar;
import com.kh_kerbabian.saveme.R;
import com.kh_kerbabian.saveme.databinding.ActivityMainBinding;
import com.kh_kerbabian.saveme.databinding.FragmentDashboardBinding;
import com.kh_kerbabian.saveme.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment  {

    private FragmentHomeBinding binding;
    private Spinner spinnerAccount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
      //  NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        String[] accounts = getResources().getStringArray(R.array.AccountsStrings);
        ArrayAdapter<String>accountAdapter = new ArrayAdapter<String>(requireContext(),
                androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                accounts);
        accountAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.spinnerAccount.setAdapter(accountAdapter);


        String[] category = getResources().getStringArray(R.array.CategoryStrings);
        ArrayAdapter<String>categoryAdapter = new ArrayAdapter<String>(requireContext(),
                androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                category);
        categoryAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.spinnerCategory.setAdapter(categoryAdapter);





        binding.fabIncome.hide();
        binding.fabExpence.hide();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //          .setAction("Action", null).show();

                binding.fabIncome.show();
                binding.fabExpence.show();
            }
        });

        binding.fabExpence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //         .setAction("Action", null).show();

                binding.fabIncome.hide();
                binding.fabExpence.hide();
                binding.spinnerCategory.setEnabled(true);


                binding.textViewEI.setText("Expenses");

             /*   // NavController navController = findNavController(R.id.nav_host_fragment_activity_main);
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_add_i_e);*/

              /*  AppCompatActivity activity = (AppCompatActivity) getActivity();
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.navigation_add_i_e);*/


            }
        });

        binding.fabIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                binding.fabIncome.hide();
                binding.fabExpence.hide();
                binding.spinnerCategory.setEnabled(false);

                binding.textViewEI.setText("Income");

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