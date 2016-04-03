package com.lifestyle.organizer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.securepreferences.SecurePreferences;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private SharedPreferences securePreferences;
    private Intent intent;

    private String m_userName;
    private String m_password;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mLvDrawerMenu;
    private DrawerMenuItemAdapter mDrawerMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLvDrawerMenu = (ListView) findViewById(R.id.lv_drawer_menu);

        List<DrawerMenuItem> menuItems = generateDrawerMenuItems();
        mDrawerMenuAdapter = new DrawerMenuItemAdapter(getApplicationContext(), menuItems);
        mLvDrawerMenu.setAdapter(mDrawerMenuAdapter);

        mLvDrawerMenu.setOnItemClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

    /*    if(savedInstanceState == null){
            setFragment(0, BikeFragment.class);
        }*/

        securePreferences = new SecurePreferences(this);

        m_userName = securePreferences.getString("userName","");
        m_password = securePreferences.getString("password","");

        if(!m_userName.isEmpty() && !m_password.isEmpty())
        {
            intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }

        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                //setFragment(0, BikeFragment.class);
                break;
            case 1:
                //setFragment(1, BusFragment.class);
                break;
            case 2:
                //setFragment(2, CarFragment.class);
                break;
            case 3:
                mDrawerLayout.closeDrawer(mLvDrawerMenu);
                mLvDrawerMenu.invalidateViews();
                break;
            case 4:
                mDrawerLayout.closeDrawer(mLvDrawerMenu);
                mLvDrawerMenu.invalidateViews();
                break;
            case 5:
                mDrawerLayout.closeDrawer(mLvDrawerMenu);
                mLvDrawerMenu.invalidateViews();
                break;
        }
    }

    public void setFragment(int position, Class<? extends Fragment> fragmentClass) {
        try {
            Fragment fragment = fragmentClass.newInstance();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment, fragmentClass.getSimpleName());
            fragmentTransaction.commit();

            mLvDrawerMenu.setItemChecked(position, true);
            mDrawerLayout.closeDrawer(mLvDrawerMenu);
            mLvDrawerMenu.invalidateViews();
        }
        catch (Exception ex){
            Log.e("setFragment", ex.getMessage());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mLvDrawerMenu)) {
            mDrawerLayout.closeDrawer(mLvDrawerMenu);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private List<DrawerMenuItem> generateDrawerMenuItems() {
        String[] itemsText = getResources().getStringArray(R.array.nav_drawer_items);
        TypedArray itemsIcon = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        List<DrawerMenuItem> result = new ArrayList<DrawerMenuItem>();

        for (int i = 0; i < itemsText.length; i++) {
            DrawerMenuItem item = new DrawerMenuItem();
            item.setText(itemsText[i]);
            item.setIcon(itemsIcon.getResourceId(i, -1));
            result.add(item);
        }

        return result;
    }
}
