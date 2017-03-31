package org.gec.cs.gsl;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StandingsRecyclerAdapter extends RecyclerView.Adapter<StandingsRecyclerAdapter.MyViewHolder> {
  private Context mContext;
  private List<String> mteams,mPlayed,mWon,mDraw,mLost,mGd,mPoint;

  public StandingsRecyclerAdapter(Context context, List<String> mteams,List<String> mPlayed,List<String> mWon,List<String> mDraw,List<String> mLost,List<String> mGd,List<String> mPoint) {
    mContext = context;
    this.mteams = mteams;
    this.mPlayed = mPlayed;
    this.mWon=mWon;
    this.mDraw=mDraw;
    this.mLost=mLost;
    this.mGd=mGd;
    this.mPoint=mPoint;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
        mContext).inflate(R.layout.table, parent, false));
    return holder;
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    if(position%2==1)
//      holder.views.setBackgroundColor(Color.parseColor("#FF4081"));
    holder.teams.setText(mteams.get(position));
    holder.Played.setText(mPlayed.get(position));
    holder.Won.setText(mWon.get(position));
    holder.Draw.setText(mDraw.get(position));
    holder.Lost.setText(mLost.get(position));
    holder.Gd.setText(mGd.get(position));
    holder.Point.setText(mPoint.get(position));
  }

  @Override
  public int getItemCount() {
    return mPlayed.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {
    View views;
    TextView teams,Played,Won,Draw,Lost,Gd,Point;

    public MyViewHolder(View view) {
      super(view);
//      views =  view.findViewById(R.id.stroke);
      teams = (TextView) view.findViewById(R.id.textView1);
      Played = (TextView) view.findViewById(R.id.textView2);
      Won = (TextView) view.findViewById(R.id.textView3);
      Draw = (TextView) view.findViewById(R.id.textView4);
      Lost = (TextView) view.findViewById(R.id.textView5);
      Gd = (TextView) view.findViewById(R.id.textView6);
      Point = (TextView) view.findViewById(R.id.textView7);
    }
  }
}