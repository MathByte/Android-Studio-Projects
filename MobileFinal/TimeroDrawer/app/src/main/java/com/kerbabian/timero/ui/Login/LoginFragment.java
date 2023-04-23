package com.kerbabian.timero.ui.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.kerbabian.timero.Iprefernace;
import com.kerbabian.timero.R;
import com.kerbabian.timero.TimeroDataBase.TimeroDataBase;
import com.kerbabian.timero.TimeroDataBase.entities.User;

public class LoginFragment extends Fragment {


    private Iprefernace myPref;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View rootV =  inflater.inflate(R.layout.fragment_login, container, false);


        final TextView editTextUsername = rootV.findViewById(R.id.UserNameText);
        final TextView editTextUserpass = rootV.findViewById(R.id.UserPasswordTextT);
        final Button LoginButt = rootV.findViewById(R.id.butLogin);
        final Button CreateButt = rootV.findViewById(R.id.butCreateAcc);
        final Button LogOutButt = rootV.findViewById(R.id.butLogOut);


        if(myPref.SomoneLoggedIn()){
            LoginButt.setEnabled(false);
            LogOutButt.setEnabled(true);
        }



        CreateButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                User user = new User();
                user.setName(editTextUsername.getText().toString());
                user.setPassword(editTextUserpass.getText().toString() );

                AppCompatActivity activity = (AppCompatActivity) getActivity();
                final TimeroDataBase applicationDB;
                applicationDB = (TimeroDataBase) activity.getApplicationContext();

                if(applicationDB.createAccount(user)) {
                    Toast.makeText(activity.getApplicationContext(), "Created", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(activity.getApplicationContext(), "Can't create an account, must be exist!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LoginButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                LoginButt.setEnabled(false);
                LogOutButt.setEnabled(true);

                User user = new User();
                user.setName(editTextUsername.getText().toString());
                user.setPassword(editTextUserpass.getText().toString() );


                AppCompatActivity activity = (AppCompatActivity) getActivity();
                final TimeroDataBase applicationDB;
                applicationDB = (TimeroDataBase) activity.getApplicationContext();
                int useIDD = applicationDB.loginUser(user);
                if( useIDD > -1) {
                    ((TextView) activity.findViewById(R.id.textViewLoginStatus)).setText("Welcome " + user.getName());

                    Toast.makeText(activity.getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();



                    //Object [] settingsOfUser = applicationDB.getUserSettings(useIDD);
                    myPref.setUserID(useIDD);

                    activity.onBackPressed();

                }
                else
                {
                    Toast.makeText(activity.getApplicationContext(), "Can't login, must be wrong password!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        LogOutButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPref.SomoneLoggedOff();


                LoginButt.setEnabled(true);
                LogOutButt.setEnabled(false);


                AppCompatActivity activity = (AppCompatActivity) getActivity();
                ((TextView) activity.findViewById(R.id.textViewLoginStatus)).setText(R.string.nav_header_subtitle);

            }
        });

        return rootV;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myPref = (Iprefernace) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        myPref = null;
    }



}