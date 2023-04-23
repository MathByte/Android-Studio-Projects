package com.kh_kerbabian.saveme.ui.login;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kh_kerbabian.saveme.R;


public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;





    public LoginFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootV =  inflater.inflate(R.layout.fragment_login, container, false);
        final TextView editTextUsername = rootV.findViewById(R.id.UserNameText);
        final TextView editTextUserpass = rootV.findViewById(R.id.UserPasswordTextT);
        final Button LoginButt = rootV.findViewById(R.id.butLogin);
        final Button CreateButt = rootV.findViewById(R.id.butCreateAcc);
        final Button LogOutButt = rootV.findViewById(R.id.butLogOut);
        CreateButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                updateUI(currentUser);
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                /*mAuth.createUserWithEmailAndPassword("asd@gmail.com","A23!asdas")
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(activity.getApplicationContext(), "comp", Toast.LENGTH_SHORT).show();

                                }
                                Toast.makeText(activity.getApplicationContext(), "not comp", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(activity.getApplicationContext(), "Succ", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity.getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(activity.getApplicationContext(), "canc", Toast.LENGTH_SHORT).show();

                            }
                        });*/

                mAuth.signInAnonymously()
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "signInAnonymously:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "signInAnonymously:failure", task.getException());
                                    Toast.makeText(activity.getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });

            }
        });
        return rootV;
    }

    private void updateUI(FirebaseUser user) {
    }
}