package org.gec.cs.gsl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ResultsRecyclerAdapter extends RecyclerView.Adapter<ResultsRecyclerAdapter.MyViewHolder> {
  private Context mContext;
  private List<String> mMatches,mDate,mNos,mWk,mS1,ms2,mid1,mid2;
  private Context context;

  public ResultsRecyclerAdapter(Context context, List<String> matches, List<String> date, List<String> no, List<String> wk, List<String> s1, List<String> s2, List<String> id1, List<String> id2) {
    mContext = context;
    mMatches = matches;
    mDate = date;
    mNos = no;
    mWk = wk;
    mS1 = s1;
    ms2 = s2;
    mid1 = id1;
    mid2 = id2;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
        mContext).inflate(R.layout.item_results, parent, false));
    return holder;
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, final int position) {
    if(position%2==1)
      holder.views.setBackgroundColor(Color.parseColor("#FF4081"));
    holder.match.setText(mMatches.get(position));
    holder.date.setText(mDate.get(position));
    holder.mNo.setText(mWk.get(position));
    holder.root.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final Intent intent =  new Intent(context, ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("mno",mNos.get(position));
        bundle.putString("match",mMatches.get(position));
        bundle.putString("date",mDate.get(position));
        bundle.putString("team1",mS1.get(position));
        bundle.putString("team2",ms2.get(position));
        bundle.putString("mid1",mid1.get(position));
        bundle.putString("mid2",mid2.get(position));
        intent.putExtras(bundle);
        context.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return mMatches.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {
    LinearLayout root;
    View views;
    TextView match,date,mNo;

    public MyViewHolder(View view) {
      super(view);
      context = itemView.getContext();
      root = (LinearLayout) view.findViewById(R.id.rootView);
      views =  view.findViewById(R.id.stroke);
      match = (TextView) view.findViewById(R.id.textMatch);
      date = (TextView) view.findViewById(R.id.textDate);
      mNo = (TextView) view.findViewById(R.id.textMno);
    }
  }
}