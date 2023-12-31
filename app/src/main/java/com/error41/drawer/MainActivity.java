package com.error41.drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {



    private DrawerLayout drawerLayout;

    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


                  // toolbar

                    toolbar=findViewById(R.id.toolbar);
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);


                    // Drawer view

                 drawerLayout=findViewById(R.id.myDrawerLayout);

                 // Drawer Navigation View


                navigationView=findViewById(R.id.nvView);

                setupDrawerContent(navigationView);



    }

    private void setupDrawerContent(NavigationView navigationView) {


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                selectDrawerItem(item);

                return true;
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void selectDrawerItem(MenuItem item) {

                    Fragment fragment=null;
                   Class fragmentClass;

                   if (item.getItemId()==R.id.nav_first_fragment) {


                       fragmentClass = FirstFragment.class;

                   } else if (item.getItemId()==R.id.nav_second_fragment) {

                       fragmentClass= SecondFragment.class;
                   } else if (item.getItemId()==R.id.nav_third_fragment) {

                       fragmentClass= ThirdFragment.class;
                   }
                    else

                      fragmentClass= FirstFragment.class;

                  try {
                      fragment =(Fragment) fragmentClass.newInstance();
                  } catch (IllegalAccessException e) {
                    e.printStackTrace();

                  } catch (InstantiationException e) {
                      e.printStackTrace();
                  }


        FragmentManager fragmentManager =getSupportFragmentManager();
                  fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();



             item.setChecked(true);
             setTitle(item.getTitle());


             drawerLayout.closeDrawers();


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
            drawerLayout.openDrawer(GravityCompat.START);

            return true;

        }

        return super.onOptionsItemSelected(item);
    }


}