package org.gec.cs.gsl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tomer.fadingtextview.FadingTextView;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SquadFragment extends Fragment {
  private RecyclerView mRecyclerView;
  private StandingRecyclerAdapter mAdapter;
  private AVLoadingIndicatorView avLoadingIndicatorView;
  private FadingTextView fadingTextView;

  private List<String> mPlayer,mPos,mGames,mGoals,mClean;
  private static final String ARG_TITLE = "title";
  private String mTitle;

  public static SquadFragment getInstance(String title) {
    SquadFragment fra = new SquadFragment();
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
    mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclervalue);
    avLoadingIndicatorView =(AVLoadingIndicatorView) v.findViewById(R.id.loading) ;
    fadingTextView =(FadingTextView) v.findViewById(R.id.textView) ;

    initData();

    fadingTextView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        fadingTextView.setVisibility(View.GONE);
        initData();
      }
    });

    return v;
  }

  protected void initData() {
    mPlayer = new ArrayList<>();
    mPos = new ArrayList<>();
    mGames = new ArrayList<>();
    mGoals = new ArrayList<>();
    mClean = new ArrayList<>();

    String URL = "http://gsl-gec.esy.es/gsl/playerlist.php?tid="+mTitle;
    Log.e("URL",URL);
    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {
        try {
          JSONArray jsonArray = (JSONArray) response.get("players");
          mPlayer.clear();
          mPos.clear();
          mGames.clear();
          mGoals.clear();
          mClean.clear();
          if (jsonArray.length() > 0) {
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
              jsonObject = (JSONObject) jsonArray.get(i);
              mPlayer.add(jsonObject.getString("name"));
              switch (jsonObject.getString("Position")){
                case "0": mPos.add("Forward");
                  break;
                case "1": mPos.add("Defender");
                  break;
                case "2": mPos.add("Goal Keeper");
                  break;
              }
                mGames.add("Goals\n"+jsonObject.getString("goals"));
              mGoals.add(jsonObject.getString("goals"));
              mClean.add(jsonObject.getString("cs"));
            }
            avLoadingIndicatorView.hide();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
            mRecyclerView.setAdapter(mAdapter = new StandingRecyclerAdapter(mRecyclerView.getContext(), mPlayer, mPos,mGames,mGoals,mClean));
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }

      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        avLoadingIndicatorView.hide();
        fadingTextView.setVisibility(View.VISIBLE);
      }
    });
    AppController.getInstance().addToRequestQueue(jsonObjectRequest, "req_player");
  }
}