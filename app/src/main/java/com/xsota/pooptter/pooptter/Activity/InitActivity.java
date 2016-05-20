package com.xsota.pooptter.pooptter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.xsota.pooptter.pooptter.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InitActivity extends AppCompatActivity {
  public static final String TAG = "InitActivity";

  @Bind(R.id.login_button) TwitterLoginButton twitterLoginButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (Twitter.getSessionManager().getActiveSession() != null){ //ログイン中
     gotoMainActivity();
    }

    setContentView(R.layout.activity_init);

    ButterKnife.bind(this);

    twitterLoginButton.setCallback(new Callback<TwitterSession>() {
      @Override
      public void success(Result<TwitterSession> result) {
        Log.d(TAG, "ログインせいこう");
        gotoMainActivity();
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

  private void gotoMainActivity(){
    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(intent);
  }
}
