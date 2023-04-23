package com.kerbabian.timero;

import static com.kerbabian.timero.Notificatios.Notifications.CHANNEL_1_ID;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.kerbabian.timero.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Iprefernace{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private SharedPreferences sharedPref;
    private NotificationManagerCompat notificationManager;
    private int LogedInUserId = 0;
    private boolean SomeLogedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AddPromodoFragment apf = new AddPromodoFragment();
        ft.add(R.id.drawer_layout, apf);
        ft.commit();*/

        notificationManager = NotificationManagerCompat.from(this);

        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);




        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_login, R.id.nav_stats, R.id.nav_settings, R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }







    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "Theme = " + String.valueOf(sharedPref.getBoolean("Theme",false)), Toast.LENGTH_SHORT).show();
    }




    public void Channel1(String st,String sm) {

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_menu_gallery)
                .setContentTitle(st)
                .setContentText(sm)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }



    @Override
    public int getUserID() {
        return LogedInUserId;
    }

    @Override
    public boolean SomoneLoggedIn() {
        return SomeLogedIn;
    }

    @Override
    public void SomoneLoggedOff() {
        LogedInUserId = -1;
        SomeLogedIn = false;
    }

    @Override
    public void setUserID(int id) {
        LogedInUserId = id;
        SomeLogedIn = true;

    }


}