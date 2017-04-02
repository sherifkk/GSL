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

public class FixturesFragment extends Fragment {
  private RecyclerView mRecyclerView;
  private FixturesRecyclerAdapter mAdapter;
  private AVLoadingIndicatorView avLoadingIndicatorView;
  private FadingTextView fadingTextView;
  private List<String> mMatches,mshort1,mshort2,mDate,mNo,mWK,mid1,mid2;
  private static final String ARG_TITLE = "title";
  private String mTitle;

  public static FixturesFragment getInstance(String title) {
    FixturesFragment fra = new FixturesFragment();
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
    mMatches = new ArrayList<>();
    mDate = new ArrayList<>();
    mNo = new ArrayList<>();
    mWK = new ArrayList<>();
    mshort1 = new ArrayList<>();
    mshort2 = new ArrayList<>();
    mid1 = new ArrayList<>();
    mid2 = new ArrayList<>();

    String URL = "http://gsl-gec.esy.es/gsl/fixtures.php";

    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {
        try {
          JSONArray jsonArray = (JSONArray) response.get("fixture");
          mMatches.clear();
          mDate.clear();
          mNo.clear();
          mWK.clear();
          mshort1.clear();
          mshort2.clear();
          mid1.clear();
          mid2.clear();
          if (jsonArray.length() > 0) {
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
              jsonObject = (JSONObject) jsonArray.get(i);
              mMatches.add(jsonObject.getString("team1")+ " vs "+jsonObject.getString("team2"));
              mDate.add(jsonObject.getString("datetime"));
              mNo.add("Match "+jsonObject.getString("mid"));
              mWK.add("Week "+jsonObject.getString("matchweek"));
              mshort1.add(jsonObject.getString("fts"));
              mshort2.add(jsonObject.getString("sts"));
              mid1.add(jsonObject.getString("T1id"));
              mid2.add(jsonObject.getString("T2id"));
            }
            avLoadingIndicatorView.hide();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
            mRecyclerView.setAdapter(mAdapter = new FixturesRecyclerAdapter(mRecyclerView.getContext(),mMatches,mDate,mNo,mWK,mshort1,mshort2,mid1,mid2));
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
    AppController.getInstance().addToRequestQueue(jsonObjectRequest,"req_fixture");
  }
}