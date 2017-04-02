package org.gec.cs.gsl;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FixtureRecyclerAdapter extends RecyclerView.Adapter<FixtureRecyclerAdapter.MyViewHolder> {
  private Context mContext;
  private List<String> mPlayer,mPos;
  private Context context;



  public FixtureRecyclerAdapter(Context context, List<String> player, List<String> pos) {
    mContext = context;
    mPlayer = player;
    mPos = pos;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
        mContext).inflate(R.layout.item_fixture, parent, false));
    return holder;
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, final int position) {
    if(position%2==1)
      holder.views.setBackgroundColor(Color.parseColor("#FF4081"));
    holder.player.setText(mPlayer.get(position));
    holder.pos.setText(mPos.get(position));
  }

  @Override
  public int getItemCount() {
    return mPlayer.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {
    View views;
    TextView player,pos;

    public MyViewHolder(View view) {
      super(view);
      context = itemView.getContext();
      views =  view.findViewById(R.id.stroke);
      player = (TextView) view.findViewById(R.id.textPlayer);
      pos = (TextView) view.findViewById(R.id.textPos);
    }
  }
}