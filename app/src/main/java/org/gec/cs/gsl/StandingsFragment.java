package org.gec.cs.gsl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class StandingsFragment extends Fragment {
  private RecyclerView mRecyclerView;
  private StandingsRecyclerAdapter mAdapter;
    private AVLoadingIndicatorView avLoadingIndicatorView;
    private FadingTextView fadingTextView;
  private List<String> mTno, mteams,mWon,mDraw,mLost,mGF,mGA,mGd,mPoint;
  private static final String ARG_TITLE = "title";
  private String mTitle;

  public static StandingsFragment getInstance(String title) {
    StandingsFragment fra = new StandingsFragment();
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
      mTno = new ArrayList<>();
      mteams = new ArrayList<>();
      mWon = new ArrayList<>();
      mDraw= new ArrayList<>();
      mLost = new ArrayList<>();
      mGF = new ArrayList<>();
      mGA = new ArrayList<>();
      mGd = new ArrayList<>();
      mPoint = new ArrayList<>();

      String URL = "http://gsl-gec.esy.es/gsl/standings.php";

      final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
          @Override
          public void onResponse(JSONObject response) {
              try {
                  JSONArray jsonArray = (JSONArray) response.get("standings");
                  if (jsonArray.length() > 0) {
                      JSONObject jsonObject;
                            mTno.clear();
                        mteams.clear();
                          mWon.clear();
                          mDraw.clear();
                          mLost.clear();
                          mGF.clear();
                          mGA.clear();
                          mGd.clear();
                          mPoint.clear();
                      for (int i = 0; i < jsonArray.length(); i++) {
                          jsonObject = (JSONObject) jsonArray.get(i);

                          mTno.add(jsonObject.getString("tno"));
                          mteams.add(jsonObject.getString("tname"));
                          mWon.add("Won: "+jsonObject.getString("won"));
                          mDraw.add("Draw: "+jsonObject.getString("draw"));
                          mLost.add("Loss: "+jsonObject.getString("loss"));
                          mGF.add("GF: "+jsonObject.getString("gf"));
                          mGA.add("GA: "+jsonObject.getString("ga"));

                          int G = Integer.parseInt(jsonObject.getString("gf")) - Integer.parseInt(jsonObject.getString("ga"));
                          if(G > 0)
                            mGd.add("GD: +"+Integer.toString(G));
                          else
                              mGd.add("GD: "+Integer.toString(G));
                          mPoint.add("Points "+Integer.toString(Integer.parseInt(jsonObject.getString("won"))*3+Integer.parseInt(jsonObject.getString("draw"))));
                      }
                      avLoadingIndicatorView.hide();
                      mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
                      mRecyclerView.setAdapter(mAdapter = new StandingsRecyclerAdapter(mRecyclerView.getContext(),mTno,mteams,mWon,mDraw,mLost,mGF,mGA,mGd,mPoint));
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
      AppController.getInstance().addToRequestQueue(jsonObjectRequest,"req_result");
  }

}