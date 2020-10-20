package com.example.scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Basics extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        menu = navigationView.getMenu();

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        LinearLayout btnMean = (LinearLayout)findViewById(R.id.btnMean);
        LinearLayout btnMedian = (LinearLayout)findViewById(R.id.btnMedian);
        LinearLayout btnMode = (LinearLayout)findViewById(R.id.btnMode);
        LinearLayout btnStDeviation = (LinearLayout)findViewById(R.id.btnStDeviation);
        LinearLayout btnVariance = (LinearLayout)findViewById(R.id.btnVariance);
        LinearLayout btnCoeffVariance = (LinearLayout)findViewById(R.id.btnCoeffVariance);
        LinearLayout btnGeoMean = (LinearLayout)findViewById(R.id.btnGeometricMean);
        LinearLayout btnPercentile = (LinearLayout)findViewById(R.id.btnPercentile);

        btnMean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basics.this, InputBasics.class);
                setType(intent,"mean");//,op);
                startActivity(intent);
            }
        });

        btnMedian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basics.this, InputBasics.class);
                setType(intent,"median");//,op);
                startActivity(intent);
            }
        });

        btnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basics.this, InputBasics.class);
                setType(intent,"mode");//,op);
                startActivity(intent);
            }
        });

        btnStDeviation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basics.this, InputBasics.class);
                setType(intent,"standard deviation");//,op);
                startActivity(intent);
            }
        });

        btnVariance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basics.this, InputBasics.class);
                setType(intent,"variance");//,op);
                startActivity(intent);
            }
        });

        btnCoeffVariance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basics.this, InputBasics.class);
                setType(intent,"coefficient of variance");//,op);
                startActivity(intent);
            }
        });

        btnGeoMean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basics.this, InputBasics.class);
                setType(intent,"geometric mean");//,op);
                startActivity(intent);
            }
        });

        btnPercentile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basics.this, InputBasics.class);
                setType(intent,"percentile");//,op);
                startActivity(intent);
            }
        });
    }

    private void setType(Intent intent, String formula) {
        Bundle b = new Bundle();
        b.putString("formula", formula);
        intent.putExtras(b);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(Basics.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_formulas:
                Intent intent2 = new Intent(Basics.this, EqFormulas.class);
                startActivity(intent2);
                break;

            case R.id.nav_help:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_rate:
                Toast.makeText(this, "Rate", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
