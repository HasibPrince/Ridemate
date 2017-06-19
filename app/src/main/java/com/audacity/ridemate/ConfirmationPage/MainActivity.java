package com.audacity.ridemate.ConfirmationPage;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.audacity.ridemate.ClientPage.ClientsFragment;
import com.audacity.ridemate.ClientPage.ClientsPresenter;
import com.audacity.ridemate.R;
import com.audacity.ridemate.Utils.Injector;
import com.audacity.ridemate.Utils.ViewUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int backPressedCount;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setContentView(R.layout.activity_main);
        init();
        getSupportActionBar().setElevation(0);
        setupWindowAnimations();
    }

    private void init() {
        Toolbar toolbar = initToolbar();

        initDrawer(toolbar);

        initNavigationView();
    }

    private void initNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.map));
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Confirmation");
        return toolbar;
    }

    private void initDrawer(Toolbar toolbar) {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupWindowAnimations() {
        //Fade fade = null;
        Explode explode = null;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //fade = new Fade();
            //fade.setDuration(3000);
            explode = new Explode();
            explode.setDuration(1000);
            getWindow().setEnterTransition(explode);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (handleBackPress(drawer)) return;
        super.onBackPressed();
    }

    private boolean handleBackPress(DrawerLayout drawer) {
        try {
            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
            if (backStackEntryCount < 2) {
                drawer.openDrawer(Gravity.LEFT);
                backPressedCount++;
                handleAppFinish();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void handleAppFinish() {
        if (backPressedCount == 2) {
            Toast.makeText(this, "Press again to exit the app", Toast.LENGTH_LONG).show();
        }
        if (backPressedCount > 2) {
            //super.onCreate(null);

            FragmentManager fm = this.getSupportFragmentManager();
            for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
            //mDrawer.setCheckedItem(R.id.navigationApraisal);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.replaceExtras(new Bundle());
            intent.putExtra("EXIT", true);// ***Change Here***
            startActivity(intent);

            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.map) {
            launchMapFragment();
        } else if (id == R.id.cliets) {
            launchClientFragment();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void launchClientFragment() {
        ClientsFragment fragment = ClientsFragment.newInstance();
        ViewUtils.addFragmentToActivity(getSupportFragmentManager(),fragment, R.id.container);
        getSupportActionBar().setTitle("Clients");
        new ClientsPresenter(fragment
                , Injector.getClientRepository(Injector.getLocalDataSource()
                , Injector.getRemoteDataSource()));
    }

    private void launchMapFragment() {
        MapFragment mapFragment = MapFragment.newInstance();
        ViewUtils.addFragmentToActivity(getSupportFragmentManager(), mapFragment, R.id.container);
        getSupportActionBar().setTitle("Confirmation");
        new MapPresenter(mapFragment);
    }

}
