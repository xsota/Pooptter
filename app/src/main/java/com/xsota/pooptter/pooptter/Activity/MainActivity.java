package com.xsota.pooptter.pooptter.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.xsota.pooptter.pooptter.R;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
  private final String TAG = "MainActivity";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    TwitterAuthConfig authConfig = new TwitterAuthConfig(getResources().getString(R.string.twitter_key), getResources().getString(R.string.twitter_secret));
    Fabric.with(this, new Twitter(authConfig));

    setContentView(R.layout.activity_main);


  }


}
