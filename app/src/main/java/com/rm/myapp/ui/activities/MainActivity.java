package com.rm.myapp.ui.activities;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.rm.myapp.R;
import com.rm.myapp.helper.Constants;
import com.rm.myapp.helper.SharedHelper;
import com.rm.myapp.helper.UserConstant;
import com.rm.myapp.services.MyService;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        View hView = navigationView.getHeaderView(0);
        TextView textView = hView.findViewById(R.id.textView);
        String name = SharedHelper.getKey(MainActivity.this, UserConstant.name);
        textView.setText(name);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_sendLocation, R.id.nav_getLocation)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        String gender = SharedHelper.getKey(MainActivity.this,UserConstant.gender);
        if (gender.equalsIgnoreCase("male")){
            navigationView.getMenu().findItem(R.id.nav_sendLocation).setVisible(false);
        }

        enableAutoStart();
        startService(new Intent(MainActivity.this, MyService.class));
        /*if (btStartService.getText().toString().equalsIgnoreCase(getString(R.string.start_service))) {
            startService(new Intent(MainActivity.this, MyService.class));
            btStartService.setText(getString(R.string.stop_service));
            tvText.setVisibility(View.VISIBLE);
        } else {
            stopService(new Intent(MainActivity.this, MyService.class));
            btStartService.setText(getString(R.string.start_service));
            tvText.setVisibility(View.GONE);
        }*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void enableAutoStart() {
        for (Intent intent : Constants.AUTO_START_INTENTS) {
            if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                new MaterialDialog.Builder(this).title(R.string.enable_autostart)
                        .content(R.string.ask_permission)
                        .theme(Theme.LIGHT)
                        .positiveText(  getString(R.string.allow))
                        .onPositive((dialog, which) -> {
                            try {
                                for (Intent intent1 : Constants.AUTO_START_INTENTS)
                                    if (getPackageManager().resolveActivity(intent1, PackageManager.MATCH_DEFAULT_ONLY)
                                            != null) {
                                        startActivity(intent1);
                                        break;
                                    }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        })
                        .show();
                break;
            }
        }
    }

    public boolean checkServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(
                    Integer.MAX_VALUE)) {
                if (getString(R.string.my_service_name).equals(service.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }
}