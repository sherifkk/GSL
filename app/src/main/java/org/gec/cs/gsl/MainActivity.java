package org.gec.cs.gsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  private ArrayList<Fragment> fragments;
  private final String[] mTitles = { "Fixture", "Result", "Standings", "Scorers"};
  private ViewPager mViewPager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    initFragments();
    initViewPager();

    int[] mImageArray = new int[]{
            R.drawable.img5,
            R.drawable.img4,
            R.drawable.img7,
            R.drawable.img1};
    int[] mColorArray = new int[]{
            android.R.color.holo_blue_light,
            android.R.color.holo_red_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_green_light};

    CoordinatorTabLayout mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
    mCoordinatorTabLayout.setTitle("GSL")
        .setBackEnable(true)
        .setImageArray(mImageArray, mColorArray)
        .setupWithViewPager(mViewPager);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);}

  private void initFragments() {
    fragments = new ArrayList<>();
    fragments.add(FixturesFragment.getInstance(mTitles[0]));
    fragments.add(ResultsFragment.getInstance(mTitles[1]));
    fragments.add(StandingsFragment.getInstance(mTitles[2]));
    fragments.add(ScorerFragment.getInstance(mTitles[3]));
  }

  private void initViewPager() {
    mViewPager = (ViewPager) findViewById(R.id.vp);
    mViewPager.setOffscreenPageLimit(4);
    mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fragments, mTitles));
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_developers) {
      startActivity(new Intent(MainActivity.this,AboutActivity.class));
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody") @Override public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_fixture) {
      mViewPager.setCurrentItem(0);
    } else if (id == R.id.nav_result) {
      mViewPager.setCurrentItem(1);
    } else if (id == R.id.nav_standings) {
      mViewPager.setCurrentItem(2);
    } else if (id == R.id.nav_scorers) {
      mViewPager.setCurrentItem(3);
    }
    else if (id == R.id.nav_about) {
      startActivity(new Intent(MainActivity.this,AboutActivity.class));
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
