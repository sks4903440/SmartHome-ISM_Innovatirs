package com.isminnovators.arduism;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SensibleCharging.Sensiblesenddata, AmbiTemp.AmbiTempsenddata
        , DynamicLighting.Dynamiclightingsenddata, SmartSolutions.SmartSolutionssenddata {
    BtSendData btSendData = new BtSendData();
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            Log.e("Fatal Error", "Bluetooth Not supported. Aborting.");
        } else {
            if (btAdapter.isEnabled()) {
                Log.d("Bluetooth", "...Bluetooth is enabled...");
            } else {
                Intent enableBtIntent = new Intent(btAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 0);
            }

        }
        btSendData.btconnect();
        btSendData.sendData("@1:1:100");
        btSendData.sendData("@1:2:5");
        btSendData.sendData("@3:1:25");

        sharedPreferences=getSharedPreferences("fan2ambitemp",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("fanstate",-1);
        editor.commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ft.addToBackStack(null);
        ft.replace(R.id.content_main, new Home()).commit();


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            {
                int count = getFragmentManager().getBackStackEntryCount();

                if (count == 0) {
                    super.onBackPressed();
                    //additional code
                } else {
                    getFragmentManager().popBackStack();
                    Fragment current = getFragmentManager().findFragmentById(R.id.content_main);
                    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                    navigationView.setCheckedItem(current.getId());
                }
            }
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
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra("android.intent.exra.SUBJECT", "txt to share");
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hi Friends!!!\nI have got the android app ArduISM for Smart Home ... \n" +
                    "Download now-\nhttp://www.isminnovators.com/downloads/androidapk.php");
            startActivity(shareIntent);
            return true;
        }
        if (id == R.id.about) {
            ft.addToBackStack(null);
            ft.replace(R.id.content_main, new About()).commit();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (id == R.id.nav_sensiblecharge) {
            ft.addToBackStack(null);
            ft.replace(R.id.content_main, new SensibleCharging()).commit();
        } else if(id==R.id.nav_home){
            ft.replace(R.id.content_main,new Home()).commit();
        }
        else if (id == R.id.nav_dynamiclighting) {
            ft.addToBackStack(null);
            ft.replace(R.id.content_main, new DynamicLighting()).commit();
        } else if (id == R.id.nav_ambitemp) {
            ft.addToBackStack(null);
            ft.replace(R.id.content_main, new AmbiTemp()).commit();
        } else if (id == R.id.nav_smartsolutions) {
            ft.addToBackStack(null);
            ft.replace(R.id.content_main, new SmartSolutions()).commit();
        } else if (id == R.id.nav_feedback) {
            ft.addToBackStack(null);
            ft.replace(R.id.content_main, new Feedback()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void sensiblesenddata1(String message) {
        btSendData.sendData(message);
    }


    @Override
    public void ambitempsenddata1(String message) {
        btSendData.sendData(message);
    }

    @Override
    public void dynamiclightinhsenddata1(String message) {
        btSendData.sendData(message);
    }

    @Override
    public void smartsolutionssenddata1(String message) {
        btSendData.sendData(message);
    }


}

