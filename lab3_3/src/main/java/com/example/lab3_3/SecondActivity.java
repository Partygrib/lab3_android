package com.example.lab3_3;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.lab3_3.databinding.ActivityMain3Binding;
import com.example.lab3_3.databinding.ThirdActivity3Binding;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.lab3_3.databinding.SecondActivity3Binding;

public class SecondActivity extends AppCompatActivity implements OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    Button button1;
    Button button2;
    private SecondActivity3Binding binding;

    OnClickListener but1 = new OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SecondActivity3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.secondAppBar.toolbar2);
        DrawerLayout drawer = binding.drawerLayout2;
        NavigationView navigationView = binding.navView;

        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setTitle("Second Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button1 = (Button) findViewById(R.id.bnToFirst);
        button2 = (Button) findViewById(R.id.bnToThird);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this::onClick2);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void onClick2(View v) {
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
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
