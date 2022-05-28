package com.popov.introductiontoandroidnotes.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.popov.introductiontoandroidnotes.R;

public class MainActivity extends AppCompatActivity implements ToolbarHolder {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);

        NavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_info_notes) {

                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, new ApplicationInformationFragment()).
                            addToBackStack("action_info_notes").
                            commit();

                    drawerLayout.close();

                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {

            new AlertDialog.Builder(this)
                    .setTitle("Выход")
                    .setMessage("Вы хотите выйти из приложения?")
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            toast();
                            MainActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void toast() {
        Toast.makeText(this, "Приложение закрыто", Toast.LENGTH_SHORT).show();
    }
}