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

public class ResultFragment extends Fragment {
  private RecyclerView mRecyclerView;
  private ResultRecyclerAdapter mAdapter;

  private List<String> mMatches,mDate,mNo;
  private static final String ARG_TITLE = "title";
  private String mTitle;

  public static ResultFragment getInstance(String title) {
    ResultFragment fra = new ResultFragment();
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
    mRecyclerView.setAdapter(mAdapter = new ResultRecyclerAdapter(mRecyclerView.getContext(),mMatches,mDate,mNo));

    return v;
  }

  protected void initData() {
      mMatches = new ArrayList<>();
      mDate = new ArrayList<>();
      mNo = new ArrayList<>();
      for(int i=0;i<3;i++){
        mMatches.add("GEC Blasters 1-0 FC Pwolians");
        mDate.add("Monday, Aug 17, 5:40 pm");
        mNo.add("Match "+Integer.toString(i+1));
      }
  }

}