package org.gec.cs.gsl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static org.gec.cs.gsl.R.id.profile_image;
import static org.gec.cs.gsl.R.id.profile_image1;

public class FixtureActivity extends AppCompatActivity  {

  private ArrayList<Fragment> fragments;
  private String[] mTitles ;
  private ViewPager mViewPager;
  private TextView mMatch,mDate,mText[];
  private String mno,match,date,team1,team2;
    //salih
    private ImageView one,two;
    private final int[] img= {R.drawable.team1,R.drawable.team2,R.drawable.team3,R.drawable.team4,R.drawable.team5,R.drawable.team6,R.drawable.team7,R.drawable.team8};


    @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fixture);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      mTitles =new String[2];
      Intent in=getIntent();
      Bundle bundle = getIntent().getExtras();
      mno = bundle.getString("mno");
      match = bundle.getString("match");
      date = bundle.getString("date");
      team1 = bundle.getString("team1");
      team2 = bundle.getString("team2");
      mTitles[0] = bundle.getString("mid1");
      mTitles[1] = bundle.getString("mid2");

      initFragments();
      initViewPager();


      mText =new TextView[2];
      mMatch = (TextView) findViewById(R.id.textMatch);
      mDate = (TextView) findViewById(R.id.textdate);
      mText[0] = (TextView) findViewById(R.id.textView1);
      mText[1] = (TextView) findViewById(R.id.textView2);



        one=(ImageView)findViewById(profile_image1);
        one.setImageResource(img[Integer.parseInt(mTitles[0])-1]);
        two=(ImageView)findViewById(profile_image);
        two.setImageResource(img[Integer.parseInt(mTitles[1])-1]);
      mMatch.setText(match);
      mDate.setText(date);
      mText[0].setText(team1);
      mText[1].setText(team2);

      mText[0].setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mViewPager.setCurrentItem(0);
          }
      });
      mText[1].setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mViewPager.setCurrentItem(1);
          }
      });

      mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

          }

          @Override
          public void onPageSelected(int position) {
              mText[position].setBackground(getResources().getDrawable(R.drawable.roun_rect_white));
              mText[position].setTextColor(Color.parseColor("#495C67"));
              mText[(position+1)%2].setBackground(getResources().getDrawable(R.drawable.roun_rect_gray));
              mText[(position+1)%2].setTextColor(Color.parseColor("#f0f2ea"));
          }

          @Override
          public void onPageScrollStateChanged(int state) {

          }
      });

    }

  private void initFragments() {
    fragments = new ArrayList<>();
    fragments.add(FixtureFragment.getInstance(mTitles[0]));
    fragments.add(FixtureFragment.getInstance(mTitles[1]));
  }

  private void initViewPager() {
    mViewPager = (ViewPager) findViewById(R.id.vp);
    mViewPager.setOffscreenPageLimit(2);
    mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fragments, mTitles));
  }

  @Override public void onBackPressed() {
    super.onBackPressed();

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
      startActivity(new Intent(FixtureActivity.this,AboutActivity.class));
    }
     else if (id == android.R.id.home) {
          finish();
      }
      return super.onOptionsItemSelected(item);
  }



}
