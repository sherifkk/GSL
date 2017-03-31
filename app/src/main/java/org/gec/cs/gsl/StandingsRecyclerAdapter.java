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
  private List<String> mteams,mWon,mDraw,mLost,mGf,mGa,mGd,mPoint;

  public StandingsRecyclerAdapter(Context context, List<String> mteams,List<String> mWon,List<String> mDraw,List<String> mLost,List<String> mGF,List<String> mGA,List<String> mGd,List<String> mPoint) {
    mContext = context;
    this.mteams = mteams;
    this.mWon=mWon;
    this.mDraw=mDraw;
    this.mLost=mLost;
    this.mGf=mGd;
    this.mGa=mGd;
    this.mGd=mGd;
    this.mPoint=mPoint;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
        mContext).inflate(R.layout.item_standings, parent, false));
    return holder;
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    if(position%2>3)
      holder.views.setBackgroundColor(Color.parseColor("#FF4081"));
    holder.teams.setText(mteams.get(position));
    holder.Won.setText(mWon.get(position));
    holder.Draw.setText(mDraw.get(position));
    holder.Lost.setText(mLost.get(position));
    holder.Gf.setText(mGf.get(position));
    holder.Ga.setText(mGa.get(position));
    holder.Gd.setText(mGd.get(position));
    holder.Point.setText(mPoint.get(position));
  }

  @Override
  public int getItemCount() {
    return mteams.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {
    View views;
    TextView teams,Won,Draw,Lost,Gf,Ga,Gd,Point;

    public MyViewHolder(View view) {
      super(view);
      views =  view.findViewById(R.id.stroke);
      teams = (TextView) view.findViewById(R.id.textTeam);
      Won = (TextView) view.findViewById(R.id.textWon);
      Draw = (TextView) view.findViewById(R.id.textDraw);
      Lost = (TextView) view.findViewById(R.id.textLoss);
      Gf = (TextView) view.findViewById(R.id.textGF);
      Ga = (TextView) view.findViewById(R.id.textGA);
      Gd = (TextView) view.findViewById(R.id.textGD);
      Point = (TextView) view.findViewById(R.id.textNo);
    }
  }
}