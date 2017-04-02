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

public class ScorerFragment extends Fragment {
  private RecyclerView mRecyclerView;
  private ScorerRecyclerAdapter mAdapter;
  private AVLoadingIndicatorView avLoadingIndicatorView;
  private FadingTextView fadingTextView;
  private List<String> mPlayer,mTeam,mNo;
  private static final String ARG_TITLE = "title";
  private String mTitle;

  public static ScorerFragment getInstance(String title) {
    ScorerFragment fra = new ScorerFragment();
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
    avLoadingIndicatorView.show();
      mPlayer = new ArrayList<>();
      mTeam = new ArrayList<>();
      mNo = new ArrayList<>();

    String URL = "http://gsl-gec.esy.es/gsl/scorers.php";

    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {
        try {
          JSONArray jsonArray = (JSONArray) response.get("scorers");
          if (jsonArray.length() > 0) {
            JSONObject jsonObject ;
              mPlayer.clear();
              mTeam.clear();
              mNo.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
              jsonObject = (JSONObject) jsonArray.get(i);
              mPlayer.add(jsonObject.getString("name"));
              mTeam.add(jsonObject.getString("team"));
              mNo.add(jsonObject.getString("goal"));
            }
            avLoadingIndicatorView.hide();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
            mRecyclerView.setAdapter(mAdapter = new ScorerRecyclerAdapter(mRecyclerView.getContext(),mPlayer,mTeam,mNo));
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
    AppController.getInstance().addToRequestQueue(jsonObjectRequest,"req_scorer");

      for(int i=0;i<3;i++){

      }
  }

}