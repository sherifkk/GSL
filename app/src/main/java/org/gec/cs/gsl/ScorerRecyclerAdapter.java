package org.gec.cs.gsl;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ScorerRecyclerAdapter extends RecyclerView.Adapter<ScorerRecyclerAdapter.MyViewHolder> {
  private Context mContext;
  private List<String> mPlayer,mTeam,mNos;

  public ScorerRecyclerAdapter(Context context, List<String> players, List<String> teams, List<String> no) {
    mContext = context;
    mPlayer = players;
    mTeam = teams;
    mNos = no;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
        mContext).inflate(R.layout.item_scorer, parent, false));
    return holder;
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    if(position%2==1)
      holder.views.setBackgroundColor(Color.parseColor("#FF4081"));
    holder.player.setText(mPlayer.get(position));
    holder.team.setText(mTeam.get(position));
    holder.no.setText(mNos.get(position));
  }

  @Override
  public int getItemCount() {
    return mPlayer.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {
    View views;
    TextView player,team,no;

    public MyViewHolder(View view) {
      super(view);
      views =  view.findViewById(R.id.stroke);
      player = (TextView) view.findViewById(R.id.textPlayer);
      team = (TextView) view.findViewById(R.id.textTeam);
      no = (TextView) view.findViewById(R.id.textNo);
    }
  }
}