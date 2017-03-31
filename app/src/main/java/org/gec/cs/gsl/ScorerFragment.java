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

public class ScorerFragment extends Fragment {
  private RecyclerView mRecyclerView;
  private ScorerRecyclerAdapter mAdapter;

  private List<String> mPlayer,mTeam,mNo;
  private static final String ARG_TITLE = "title";
  private String mTitle;

  public static ScorerFragment getInstance(String title) {
    ScorerFragment fra = new ScorerFragment();
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
    mRecyclerView.setAdapter(mAdapter = new ScorerRecyclerAdapter(mRecyclerView.getContext(),mPlayer,mTeam,mNo));

    return v;
  }

  protected void initData() {
      mPlayer = new ArrayList<>();
      mTeam = new ArrayList<>();
      mNo = new ArrayList<>();
      for(int i=0;i<3;i++){
        mPlayer.add("Mohammed Salih");
        mTeam.add("GEC Blasters");
        mNo.add("40");
      }
  }

}