package com.example.lab3_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.lab3_2.databinding.AboutActivity2Binding;

public class AboutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AboutActivity2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AboutActivity2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.aboutBar.toolbarAbout);
        DrawerLayout drawer = binding.drawerLayoutAbout;
        NavigationView navigationView = binding.navView;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.aboutBar.toolbarAbout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_about);
        drawer.closeDrawer(GravityCompat.START);
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        return false;
    }
}
