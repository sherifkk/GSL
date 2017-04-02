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

public class ResultFragment extends Fragment {
  private RecyclerView mRecyclerView;
  private ResultRecyclerAdapter mAdapter;
  private AVLoadingIndicatorView avLoadingIndicatorView;
  private FadingTextView fadingTextView;
  private List<String> mPid, mPlayer,mPos,sub,gid,goals;
  private static final String ARG_TITLE = "title";
  private String mTitle;

  public static ResultFragment getInstance(String title) {
    ResultFragment fra = new ResultFragment();
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
    mPid = new ArrayList<>();
    mPlayer = new ArrayList<>();
    mPos = new ArrayList<>();
    sub = new ArrayList<>();
    gid = new ArrayList<>();
    goals = new ArrayList<>();

    String[] parts = mTitle.split(",");

    String URL = "http://gsl-gec.esy.es/gsl/played.php?mid="+parts[0]+"&tid="+parts[1];
    Log.e("URL", URL);
    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {

        try {
        JSONArray jsonArray= (JSONArray) response.get("played");
          mPid.clear();
          mPlayer.clear();
          mPos.clear();
          if (jsonArray.length() > 0) {
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
              jsonObject = (JSONObject) jsonArray.get(i);
              mPid.add(jsonObject.getString("pid"));
              mPlayer.add(jsonObject.getString("name"));
              switch (jsonObject.getString("position")) {
                case "0":
                  mPos.add("Forward");
                  break;
                case "1":
                  mPos.add("Defender");
                  break;
                case "2":
                  mPos.add("Goal Keeper");
                  break;
              }
              sub.add(jsonObject.getString("sub"));
            }

            JSONArray jsonArray1 = (JSONArray) response.get("goals");
            if (jsonArray1.length() > 0) {
              for (int i=0 ;  i <jsonArray1.length();i++){
                jsonObject = (JSONObject) jsonArray1.get(i);
                gid.add(jsonObject.getString("pid"));
                goals.add(jsonObject.getString("no"));
              }
              avLoadingIndicatorView.hide();
              mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
              mRecyclerView.setAdapter(mAdapter = new ResultRecyclerAdapter(mRecyclerView.getContext(), mPid, mPlayer, mPos, sub,gid,goals));
            } else {
              avLoadingIndicatorView.hide();
              mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
              mRecyclerView.setAdapter(mAdapter = new ResultRecyclerAdapter(mRecyclerView.getContext(), mPlayer, mPos, sub));
            }
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