package Guzzlecode.IntentCaller.activity;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Guzzlecode.IntentCaller.R;
import Guzzlecode.IntentCaller.fragment.DashboardFragment;
import Guzzlecode.IntentCaller.fragment.MailFragment;
import Guzzlecode.IntentCaller.fragment.MapsFragment;
import Guzzlecode.IntentCaller.fragment.PhoneFragment;
import Guzzlecode.IntentCaller.fragment.WebFragment;

public class DashbaordActivity extends AppCompatActivity {

    // 1. Deklarasikan Fragment
    final Fragment fragment_maps = new MapsFragment();
    final Fragment fragment_phone = new PhoneFragment();
    final Fragment fragment_mail = new MailFragment();
    final Fragment fragment_web = new WebFragment();
    final Fragment fragment_dashboard = new DashboardFragment();

    // 2. Set Fragment Manager
    final FragmentManager fragmentManager = getSupportFragmentManager();

    // 3. Set Fragment Active
    Fragment active = fragment_dashboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setTitle("Intent Caller v1.0");

        BottomNavigationView navigationMenu = (BottomNavigationView)findViewById(R.id.navigation);
        navigationMenu.setOnNavigationItemSelectedListener(itemselectedListenerNavigation);

        navigationMenu.setSelectedItemId(R.id.navigation_dashboard);

        fragmentManager.beginTransaction().add(R.id.main_container, fragment_maps, "5").hide(fragment_maps).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, fragment_phone, "4").hide(fragment_phone).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, fragment_mail, "3").hide(fragment_mail).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, fragment_web, "2").hide(fragment_web).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, fragment_dashboard, "1").commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemselectedListenerNavigation = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    fragmentManager.beginTransaction().hide(active).show(fragment_dashboard).commit();
                    active = fragment_dashboard;
                    return true;
                case R.id.navigation_maps:
                    fragmentManager.beginTransaction().hide(active).show(fragment_maps).commit();
                    active = fragment_maps;
                    return true;
                case R.id.navigation_mail:
                    fragmentManager.beginTransaction().hide(active).show(fragment_mail).commit();
                    active = fragment_mail;
                    return true;
                case R.id.navigation_phone:
                    fragmentManager.beginTransaction().hide(active).show(fragment_phone).commit();
                    active = fragment_phone;
                    return true;
                case R.id.navigation_web:
                    fragmentManager.beginTransaction().hide(active).show(fragment_web).commit();
                    active = fragment_web;
                    return true;
            }
            return false;
        }
    };
}