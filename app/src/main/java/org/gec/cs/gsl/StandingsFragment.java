package org.gec.cs.gsl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class StandingsFragment extends Fragment {
  private RecyclerView mRecyclerView;
  private StandingsRecyclerAdapter mAdapter;

  private List<String> mteams,mPlayed,mWon,mDraw,mLost,mGd,mPoint;
  private static final String ARG_TITLE = "title";
  private String mTitle;

  public static StandingsFragment getInstance(String title) {
    StandingsFragment fra = new StandingsFragment();
    Bundle bundle = new Bundle();
    bundle.putString(ARG_TITLE, title);
    fra.setArguments(bundle);
    return fra;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = getArguments();
    mTitle = bundle.getString(ARG_TITLE);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_main, container, false);

    initData();

    mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclervalue);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
    mRecyclerView.setAdapter(mAdapter = new StandingsRecyclerAdapter(mRecyclerView.getContext(),mteams,mPlayed,mWon,mDraw,mLost,mGd,mPoint));

    return v;
  }

  protected void initData() {
      mteams = new ArrayList<>();
      mPlayed = new ArrayList<>();
      mWon = new ArrayList<>();
      mDraw= new ArrayList<>();
      mLost = new ArrayList<>();
      mGd = new ArrayList<>();
      mPoint = new ArrayList<>();
      for(int i=0;i<3;i++){
        mteams.add("GEC Blasters");
        mPlayed.add("3");
        mWon.add("1");
        mDraw.add("1");
        mLost.add("1");
        mGd.add("+1");
        mPoint.add("4");
      }
  }

}