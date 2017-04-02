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

public class StandingActivity extends AppCompatActivity  {

  private ArrayList<Fragment> fragments;
  private String[] mTitles ;
  private ViewPager mViewPager;
  private TextView mTeam,mPos,mText[];
  private String team,pos;
    private final int[] img= {R.drawable.team1,R.drawable.team2,R.drawable.team3,R.drawable.team4,R.drawable.team5,R.drawable.team6,R.drawable.team7,R.drawable.team8};
    private ImageView one;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_standing);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      mTitles =new String[3];
      Intent in=getIntent();
      Bundle bundle = getIntent().getExtras();
      team = bundle.getString("team");
      pos = bundle.getString("position");
      mTitles[0] = bundle.getString("tno");
      mTitles[1] = bundle.getString("tno");
      mTitles[2] = bundle.getString("tno");

      initFragments();
      initViewPager();


      mText =new TextView[2];
      mTeam = (TextView) findViewById(R.id.textTeam);
      mPos = (TextView) findViewById(R.id.textPos);
      mText[0] = (TextView) findViewById(R.id.textView1);
      mText[1] = (TextView) findViewById(R.id.textView2);
        one=(ImageView)findViewById(R.id.profile_image1) ;
      one.setImageResource(img[Integer.parseInt(mTitles[0])-1]);
      mTeam.setText(team);
      mPos.setText(pos);

      mText[0].setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(mText[0].getText().equals("Squad"))
                  mViewPager.setCurrentItem(0);
              else
                  mViewPager.setCurrentItem(1);
          }
      });
      mText[1].setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(mText[0].getText().equals("Squad"))
                  mViewPager.setCurrentItem(1);
              else
                  mViewPager.setCurrentItem(2);
          }
      });

      mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

          }

          @Override
          public void onPageSelected(int position) {
              if (position==0){
                  mText[0].setBackground(getResources().getDrawable(R.drawable.roun_rect_white));
                  mText[0].setTextColor(Color.parseColor("#495C67"));
                  mText[0].setText("Squad");
                  mText[1].setText("Fixture");
                  mText[1].setBackground(getResources().getDrawable(R.drawable.roun_rect_gray));
                  mText[1].setTextColor(Color.parseColor("#f0f2ea"));
              }
              else if (position==2){
                  mText[1].setBackground(getResources().getDrawable(R.drawable.roun_rect_white));
                  mText[1].setTextColor(Color.parseColor("#495C67"));
                  mText[1].setText("Result");
                  mText[0].setText("Fixture");
                  mText[0].setBackground(getResources().getDrawable(R.drawable.roun_rect_gray));
                  mText[0].setTextColor(Color.parseColor("#f0f2ea"));
              }
              else if (position==1){
                  if(mText[0].getText().equals("Squad")){
                    mText[0].setBackground(getResources().getDrawable(R.drawable.roun_rect_white));
                    mText[0].setTextColor(Color.parseColor("#495C67"));
                    mText[0].setText("Fixture");
                    mText[1].setText("Result");
                    mText[1].setBackground(getResources().getDrawable(R.drawable.roun_rect_gray));
                    mText[1].setTextColor(Color.parseColor("#f0f2ea"));
                  }
                  else {
                      mText[1].setBackground(getResources().getDrawable(R.drawable.roun_rect_white));
                      mText[1].setTextColor(Color.parseColor("#495C67"));
                      mText[1].setText("Fixture");
                      mText[0].setText("Squad");
                      mText[0].setBackground(getResources().getDrawable(R.drawable.roun_rect_gray));
                      mText[0].setTextColor(Color.parseColor("#f0f2ea"));
                  }
              }

          }

          @Override
          public void onPageScrollStateChanged(int state) {

          }
      });

    }

  private void initFragments() {
    fragments = new ArrayList<>();
    fragments.add(SquadFragment.getInstance(mTitles[0]));
    fragments.add(TeamFixturesFragment.getInstance(mTitles[1]));
      fragments.add(TeamResultsFragment.getInstance(mTitles[2]));
  }

  private void initViewPager() {
    mViewPager = (ViewPager) findViewById(R.id.vp);
    mViewPager.setOffscreenPageLimit(3);
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
          startActivity(new Intent(StandingActivity.this,AboutActivity.class));
      }
     else if (id == android.R.id.home) {
          finish();
      }
      return super.onOptionsItemSelected(item);
  }



}
