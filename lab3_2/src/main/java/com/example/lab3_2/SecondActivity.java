package com.example.lab3_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.lab3_2.databinding.SecondActivity2Binding;

public class SecondActivity extends AppCompatActivity implements OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    Button button1;
    Button button2;
    public static final int REQUEST_CODE = 1;
    private SecondActivity2Binding binding;

    OnClickListener but1 = new OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SecondActivity2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.secondAppBar.toolbar2);
        DrawerLayout drawer = binding.drawerLayout2;
        NavigationView navigationView = binding.navView;

        getSupportActionBar().setTitle("Second Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        button1 = (Button) findViewById(R.id.bnToFirst);
        button2 = (Button) findViewById(R.id.bnToThird);

        button1.setOnClickListener(but1);
        button2.setOnClickListener(this::onClick);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    finish();
                }
            });

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ThirdActivity.class);
        someActivityResultLauncher.launch(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        return false;
    }
}
