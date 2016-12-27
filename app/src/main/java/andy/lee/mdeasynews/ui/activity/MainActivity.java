package andy.lee.mdeasynews.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import andy.lee.mdeasynews.R;
import andy.lee.mdeasynews.constant.Constant;
import andy.lee.mdeasynews.ui.fragment.NewsFragment;
import andy.lee.mdeasynews.ui.fragment.VideoFragment;
import andy.lee.mdeasynews.utils.SharedPreferencesUtil;

import static andy.lee.mdeasynews.R.id.fab;
import static andy.lee.mdeasynews.R.id.toolbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private FloatingActionButton mFb;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mToggle;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initTabLayout();
        inflateMenu();
        initSearchView();
    }

    private void init() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mToolbar = (Toolbar) findViewById(toolbar);
        mToolbar.setTitle("首页");
        setSupportActionBar(mToolbar);
        mFb = (FloatingActionButton) findViewById(fab);
        mFb.setOnClickListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    private void initTabLayout() {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        if (mFragments == null) {
            mFragments = new ArrayList<>();
        }
        adapter.addFrag(new NewsFragment(), "news");
        adapter.addFrag(new VideoFragment(), "video");
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    private void inflateMenu() {
        mToolbar.inflateMenu(R.menu.main);
    }

    private void initSearchView() {
        SearchView searchView = (SearchView) mToolbar.getMenu()
                .findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
    }

    private class ViewPageAdapter extends FragmentStatePagerAdapter {
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragments.add(fragment);
            fragmentTitle.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "setting", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.night_mode) {
            SharedPreferencesUtil.setBoolean(this, Constant.MODE, true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            recreate();
            return true;
        } else if (id == R.id.day_mode) {
            SharedPreferencesUtil.setBoolean(this, Constant.MODE, false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            recreate();
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case fab:
                Snackbar.make(view, "your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
