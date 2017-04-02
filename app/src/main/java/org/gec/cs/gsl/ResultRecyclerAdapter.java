package org.gec.cs.gsl;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ResultRecyclerAdapter extends RecyclerView.Adapter<ResultRecyclerAdapter.MyViewHolder> {
  private Context mContext;
  private List<String> mPid, mPlayer,mPos, mSub, mgid, mgno;
  private Context context;

  public ResultRecyclerAdapter(Context context, List<String> player, List<String> pos, List<String> sub) {
    mContext = context;
    mPlayer = player;
    mPos = pos;
    mSub = sub;
  }

  public ResultRecyclerAdapter(Context context, List<String> id, List<String> player, List<String> pos, List<String> sub, List<String> gid, List<String> gno) {
    mContext = context;
    mPid = id;
    mPlayer = player;
    mPos = pos;
    mSub = sub;
    mgid = gid;
    mgno = gno;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
        mContext).inflate(R.layout.item_result, parent, false));
    return holder;
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, final int position) {
    holder.player.setText(mPlayer.get(position));
    holder.pos.setText(mPos.get(position));
    switch (mSub.get(position)){
      case "1": holder.views.setBackgroundColor(Color.parseColor("#FF4081"));
                holder.sub.setVisibility(View.VISIBLE);
                break;
      case "2": holder.views.setBackgroundColor(Color.parseColor("#40FF81"));
                holder.sub.setVisibility(View.VISIBLE);
                break;
    }



    if(mgid!=null &&mgid.indexOf(mPid.get(position))!=-1){

      for(int i=0;i<Integer.parseInt(mgno.get(mgid.indexOf(mPid.get(position))));i++){
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.height=28;
        params.width=28;
        params.setMargins(12,12,12,12);
        imageView.setLayoutParams(params);
        imageView.setImageResource(R.drawable.goal);
        holder.linear.addView(imageView);
      }
    }
  }

  @Override
  public int getItemCount() {
    return mPlayer.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {
    View views;
    TextView player,pos;
    ImageView sub;
    LinearLayout linear;

    public MyViewHolder(View view) {
      super(view);
      context = itemView.getContext();
      views =  view.findViewById(R.id.stroke);
      player = (TextView) view.findViewById(R.id.textPlayer);
      pos = (TextView) view.findViewById(R.id.textPos);
      sub= (ImageView) view.findViewById(R.id.subView);
      linear = (LinearLayout) view.findViewById(R.id.linear);

    }
  }
}