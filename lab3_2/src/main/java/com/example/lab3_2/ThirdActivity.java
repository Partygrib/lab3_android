package com.example.lab3_2;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.lab3_2.databinding.ThirdActivity2Binding;

public class ThirdActivity extends AppCompatActivity implements OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    Button button1;
    Button button2;
    private ThirdActivity2Binding binding;

    OnClickListener but1 = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ThirdActivity2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.thirdAppBar.toolbar3);
        DrawerLayout drawer = binding.drawerLayout3;
        NavigationView navigationView = binding.navView;

        getSupportActionBar().setTitle("Third Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        button1 = (Button) findViewById(R.id.bnToFirst);
        button2 = (Button) findViewById(R.id.bnToSecond);

        button1.setOnClickListener(but1);
        button2.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout3);
        drawer.closeDrawer(GravityCompat.START);
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        return false;
    }
}
