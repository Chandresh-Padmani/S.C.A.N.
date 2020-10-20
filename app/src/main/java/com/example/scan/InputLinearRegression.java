package com.example.scan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class InputLinearRegression extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;

    ArrayList<Model> models = new ArrayList<Model>();
    RecyclerView rvTechSolPoint;
    RvAdapterTwoInputs rvAdapter;
    Button tvAdd;
    EditText etEnterValue, etEnterValue2;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_linear_regression);

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

        rvTechSolPoint = findViewById(R.id.rv_list_item);
        tvAdd = findViewById(R.id.tv_add);
        etEnterValue = findViewById(R.id.et_enter_value);
        etEnterValue2 = findViewById(R.id.et_enter_value2);
        //tvUpdate = findViewById(R.id.tv_update);
        rvTechSolPoint.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvTechSolPoint.setLayoutManager(layoutManager);
        rvAdapter = new RvAdapterTwoInputs(getApplicationContext(), models,
                new RvAdapterTwoInputs.Onclick() {
                    @Override
                    public void onEvent(Model model, int pos) {
                        position = pos;
                        //tvUpdate.setVisibility(View.VISIBLE);
                        etEnterValue.setText(model.getValue());
                        etEnterValue2.setText(model.getValue2());
                    }
                });
        rvTechSolPoint.setAdapter(rvAdapter);
        tvAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add: {

                String value = etEnterValue.getText().toString();
                String value2 = etEnterValue2.getText().toString();
                if(value.equals("")){
                    Toast.makeText(InputLinearRegression.this,"Enter Value of X",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(value2.equals("")){
                    Toast.makeText(InputLinearRegression.this,"Enter Value of Y",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    //insertMethod(String.valueOf(etEnterName.getText()), String.valueOf(etEnterName2.getText()));

                    Model model  = new Model();
                    model.setValue(value);
                    model.setValue2(value2);
                    models.add(model);
                    rvAdapter.notifyDataSetChanged();
                }
                EditText r1 = findViewById(R.id.et_enter_value);
                r1.setText("");
                EditText r2 = findViewById(R.id.et_enter_value2);
                r2.setText("");

            }
            break;

        }
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
                Intent intent = new Intent(InputLinearRegression.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_formulas:
                Intent intent2 = new Intent(InputLinearRegression.this, EqFormulas.class);
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
