package com.portfolio.marketeasy.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;
import com.portfolio.marketeasy.R;
import com.portfolio.marketeasy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpConfig();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu,menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    private void setUpConfig(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbarMain);

        DrawerLayout drawerLayout = binding.drawerLayoutMain;
        NavigationView navigationView = binding.navViewMain;

        //En el appbar, debes indicar el nombre del id de los fragments del graph y a su vez, estos deben coincidir con los items del menu
        appBarConfiguration = new AppBarConfiguration
                                    .Builder(   R.id.nav_home,
                                                R.id.nav_profile,
                                                R.id.nav_catalogue)
                                    .setOpenableLayout(drawerLayout)
                                    .build();

        navController = Navigation.findNavController(this,R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
}