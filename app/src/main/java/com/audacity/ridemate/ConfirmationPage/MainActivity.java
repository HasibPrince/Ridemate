package com.audacity.ridemate.ConfirmationPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.audacity.ridemate.ClientPage.ClientsFragment;
import com.audacity.ridemate.ClientPage.ClientsPresenter;
import com.audacity.ridemate.R;
import com.audacity.ridemate.Utils.Injector;
import com.audacity.ridemate.Utils.ViewUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int backPressedCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Confirmation");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.map));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        try {
            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
            if (backStackEntryCount < 2) {
                drawer.openDrawer(Gravity.LEFT);
                backPressedCount++;
                handleAppFinish();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onBackPressed();
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
            //intent.setAction("");
            //intent.setData(null);
            intent.putExtra("EXIT", true);// ***Change Here***

            startActivity(intent);


            //finish();
            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.map) {
            MapFragment mapFragment = MapFragment.newInstance();
            ViewUtils.addFragmentToActivity(getSupportFragmentManager(), mapFragment,R.id.container);
            new MapPresenter(mapFragment);

        } else if (id == R.id.cliets) {

            ClientsFragment fragment = ClientsFragment.newInstance();
            ViewUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.container);
            new ClientsPresenter(fragment
                    , Injector.getClientRepository(Injector.getLocalDataSource()
                    , Injector.getRemoteDataSource()));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
