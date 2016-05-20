package com.xsota.pooptter.pooptter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.xsota.pooptter.pooptter.R;
import com.xsota.pooptter.pooptter.Utils.AccountUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
  private final String TAG = "MainActivity";

  @Bind(R.id.login_button) TwitterLoginButton twitterLoginButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    TwitterAuthConfig authConfig = new TwitterAuthConfig(getResources().getString(R.string.twitter_key), getResources().getString(R.string.twitter_secret));
    Fabric.with(this, new Twitter(authConfig));

    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    twitterLoginButton.setCallback(new Callback<TwitterSession>() {
      @Override
      public void success(Result<TwitterSession> result) {
        Log.d(TAG, "ログインせいこう");

        AccountUtil accountUtil = new AccountUtil(MainActivity.this);
        accountUtil.saveTwitterAuthToken(result.data);

      }

      @Override
      public void failure(TwitterException e) {
        Log.d(TAG, "ログインしっぱい"+e.getMessage());
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    twitterLoginButton.onActivityResult(requestCode, resultCode, data);
  }
}
