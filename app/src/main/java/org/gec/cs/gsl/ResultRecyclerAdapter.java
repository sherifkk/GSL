package org.gec.cs.gsl;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ResultRecyclerAdapter extends RecyclerView.Adapter<ResultRecyclerAdapter.MyViewHolder> {
  private Context mContext;
  private List<String> mMatches,mDate,mNos;

  public ResultRecyclerAdapter(Context context, List<String> matches, List<String> date, List<String> no) {
    mContext = context;
    mMatches = matches;
    mDate = date;
    mNos = no;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
        mContext).inflate(R.layout.item_result, parent, false));
    return holder;
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    if(position%2==1)
      holder.views.setBackgroundColor(Color.parseColor("#FF4081"));
    holder.match.setText(mMatches.get(position));
    holder.date.setText(mDate.get(position));
    holder.mNo.setText(mNos.get(position));
  }

  @Override
  public int getItemCount() {
    return mMatches.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {
    View views;
    TextView match,date,mNo;

    public MyViewHolder(View view) {
      super(view);
      views =  view.findViewById(R.id.stroke);
      match = (TextView) view.findViewById(R.id.textMatch);
      date = (TextView) view.findViewById(R.id.textDate);
      mNo = (TextView) view.findViewById(R.id.textMno);
    }
  }
}