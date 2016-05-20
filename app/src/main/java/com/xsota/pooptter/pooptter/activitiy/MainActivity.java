package com.xsota.pooptter.pooptter.activitiy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;
import com.xsota.pooptter.pooptter.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
  private final String TAG = "MainActivity";

  @BindView(R.id.listview) ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    TwitterAuthConfig authConfig = new TwitterAuthConfig(getResources().getString(R.string.twitter_key), getResources().getString(R.string.twitter_secret));
    Fabric.with(this, new Twitter(authConfig));

    setContentView(R.layout.activity_main);
    ButterKnife.setDebug(true);
    ButterKnife.bind(this);
    ButterKnife.bind(this);
    ButterKnife.bind(this);
    ButterKnife.bind(this);
    ButterKnife.bind(this);


    //listView = (ListView) findViewById(R.id.listview);

    final UserTimeline userTimeline = new UserTimeline.Builder()
        .screenName("xsota")
        .build();
    final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
        .setTimeline(userTimeline)
        .build();


    listView.setAdapter(adapter);
  }

}
