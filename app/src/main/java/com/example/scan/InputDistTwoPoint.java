package com.example.scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class InputDistTwoPoint extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;
    Button btnCalc;
    EditText etEnterValue, etEnterValue2, etEnterValue3, etEnterValue4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dist_twopoint);

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

        etEnterValue = findViewById(R.id.et_enter_value);
        etEnterValue2 = findViewById(R.id.et_enter_value2);
        etEnterValue3 = findViewById(R.id.et_enter_value3);
        etEnterValue4 = findViewById(R.id.et_enter_value4);

        Button btnCalculator = (Button) findViewById(R.id.btnCalc);
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnCalc: {

                        String value = etEnterValue.getText().toString();
                        String value2 = etEnterValue2.getText().toString();
                        String value3 = etEnterValue3.getText().toString();
                        String value4 = etEnterValue4.getText().toString();
                        if (value.equals("")) {
                            Toast.makeText(InputDistTwoPoint.this, "Enter Value of X1", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (value2.equals("")) {
                            Toast.makeText(InputDistTwoPoint.this, "Enter Value of Y1", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (value3.equals("")) {
                            Toast.makeText(InputDistTwoPoint.this, "Enter Value of X2", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (value4.equals("")) {
                            Toast.makeText(InputDistTwoPoint.this, "Enter Value of Y2", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Intent intent = new Intent(InputDistTwoPoint.this, OutputNumerical.class);
                            Bundle b = new Bundle();
                            b.putString("formula_type", "dist_two_point");
                            b.putInt("x1", Integer.valueOf(value));
                            b.putInt("y1", Integer.valueOf(value2));
                            b.putInt("x2", Integer.valueOf(value3));
                            b.putInt("y2", Integer.valueOf(value4));
                            intent.putExtras(b);
                            startActivity(intent);

                        }
                    }
                }
            }
        });

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
                Intent intent = new Intent(InputDistTwoPoint.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_formulas:
                Intent intent2 = new Intent(InputDistTwoPoint.this, EqFormulas.class);
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
