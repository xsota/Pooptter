package com.xsota.pooptter.pooptter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;
import com.xsota.pooptter.pooptter.R;

import java.util.List;

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
    ButterKnife.bind(this);


    final UserTimeline userTimeline = new UserTimeline.Builder()
        .screenName("xsota")
        .build();


    TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
    twitterApiClient.getStatusesService().homeTimeline(20, null, null, null,null,null, null, new Callback<List<Tweet>>() {

      @Override
      public void success(Result<List<Tweet>> listResult) {

        final FixedTweetTimeline timeline = new FixedTweetTimeline.Builder()
            .setTweets(listResult.data)
            .build();

       listView.setAdapter(new TweetTimelineListAdapter
           .Builder(getApplicationContext())
           .setTimeline(timeline)
           .build()
       );
      }

      @Override
      public void failure(TwitterException e) {
        Log.e(TAG, e.getMessage());
      }
    });


  }

}
