package com.quar.tpmobile;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class TabMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_menu);

        //NavigationView

        DrawerLayout drawerLayout = findViewById(R.id.TabMenu);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String[] s = {"Xaydo`vchi", "Yo`lovchi"};

        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager viewPager = findViewById(R.id.viewpage);

        viewPager.setAdapter(new ViewPageAdapter(TabMenu.this, s));
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        Toast.makeText(TabMenu.this, "Chiqish", Toast.LENGTH_SHORT).show();

        if (id == R.id.myDate)
        {
            Toast.makeText(TabMenu.this, "Mening ma`lumotlarim", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.advertisement) {
            Toast.makeText(TabMenu.this, "E`lonlarim", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.setting) {
            Toast.makeText(TabMenu.this, "Sozlamalar", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.exit){
            Toast.makeText(TabMenu.this, "Chiqish", Toast.LENGTH_SHORT).show();
        }


        DrawerLayout drawer = findViewById(R.id.TabMenu);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
