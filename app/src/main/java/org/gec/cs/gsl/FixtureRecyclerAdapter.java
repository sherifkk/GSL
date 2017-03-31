package org.gec.cs.gsl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FixtureRecyclerAdapter extends RecyclerView.Adapter<FixtureRecyclerAdapter.MyViewHolder> {
  private Context mContext;
  private List<String> mMatches,mDate,mNos;
  private Context context;

  public FixtureRecyclerAdapter(Context context, List<String> matches, List<String> date, List<String> no) {
    mContext = context;
    mMatches = matches;
    mDate = date;
    mNos = no;
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
    holder.match.setText(mMatches.get(position));
    holder.date.setText(mDate.get(position));
    holder.mNo.setText(mNos.get(position));
    holder.root.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final Intent intent =  new Intent(context, FixtureActivity.class);
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