package org.gec.cs.gsl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class StandingsRecyclerAdapter extends RecyclerView.Adapter<StandingsRecyclerAdapter.MyViewHolder> {
  private Context mContext,context;
  private List<String> mtno, mteams,mWon,mDraw,mLost,mGf,mGa,mGd,mPoint;
  private final int[] img= {R.drawable.team1,R.drawable.team2,R.drawable.team3,R.drawable.team4,R.drawable.team5,R.drawable.team6,R.drawable.team7,R.drawable.team8};

  public StandingsRecyclerAdapter(Context context, List<String> mtno,List<String> mteams,List<String> mWon,List<String> mDraw,List<String> mLost,List<String> mGF,List<String> mGA,List<String> mGd,List<String> mPoint) {
    mContext = context;
    this.mtno = mtno;
    this.mteams = mteams;
    this.mWon=mWon;
    this.mDraw=mDraw;
    this.mLost=mLost;
    this.mGf=mGF;
    this.mGa=mGA;
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
  public void onBindViewHolder(MyViewHolder holder, final int position) {
    holder.image.setImageResource(img[Integer.parseInt(mtno.get(position))-1]);
    if(position>3)
      holder.views.setBackgroundColor(Color.parseColor("#FF4081"));
    holder.teams.setText(mteams.get(position));
    holder.Won.setText(mWon.get(position));
    holder.Draw.setText(mDraw.get(position));
    holder.Lost.setText(mLost.get(position));
    holder.Gf.setText(mGf.get(position));
    holder.Ga.setText(mGa.get(position));
    holder.Gd.setText(mGd.get(position));
    holder.Point.setText(mPoint.get(position));
    holder.root.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final Intent intent =  new Intent(context, StandingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("tno",mtno.get(position));
        bundle.putString("team",mteams.get(position));
        bundle.putString("position", String.valueOf("Rank #"+(position+1)));
        intent.putExtras(bundle);
        context.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return mteams.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {
    LinearLayout root;

    View views;
    TextView teams,Won,Draw,Lost,Gf,Ga,Gd,Point;
    ImageView image;

    public MyViewHolder(View view) {
      super(view);
      context = itemView.getContext();
      root = (LinearLayout) view.findViewById(R.id.rootView);
      image= (ImageView) view.findViewById(R.id.profile_image);
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