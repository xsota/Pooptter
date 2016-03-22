package com.xsota.pooptter.pooptter.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by sota on 16/03/22.
 */
public class AccountUtil {

  private Context context;
  private final String NAME = "Account";


  /**
   * コンストラクタ
   */
  public AccountUtil(Context _context){
    context = _context; ;
  }


  /**
   * twitterTokenとか保存するぞい
   * @param session
   */
  public void saveTwitterAuthToken(TwitterSession session){
    SharedPreferences.Editor editor = getSharedPreferences().edit();
    editor.putString("token", session.getAuthToken().token);
    editor.putString("secret", session.getAuthToken().secret);
    editor.apply();
  }

  /**
   * Tokentとかもってるかでログインしてるか判断するぞい
   * @return
   */
  public boolean isTwitterLogin(){
    SharedPreferences sharedPreferences = getSharedPreferences();

    if (sharedPreferences.getString("token", null) != null){
      return true;
    }

    return false;
  }

  private SharedPreferences getSharedPreferences(){
    return context.getSharedPreferences(NAME, context.MODE_PRIVATE);
  }


}
