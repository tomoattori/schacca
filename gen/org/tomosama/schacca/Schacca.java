package org.tomosama.schacca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Schacca extends Activity {
  private static final String ACTION_INTERCEPT = "jp.r246.twicca.ACTION_EDIT_TWEET";

  // ここから本体
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent itIn = getIntent();
    String action = itIn.getAction();

     // "スチャッ"を定義しておく
    final String schaStr = "ｽﾁｬｯ (｀・ω-)▄︻┻┳一";

    // Intent経由での呼び出し時しか動きませんよっと
    if (action != null && ACTION_INTERCEPT.equals(action)) {

      // 加工用のStringBufferを準備しておく
  	  StringBuffer targStr = new StringBuffer();

      // 編集中の文字列を取得
      final String origStr = itIn.getStringExtra(Intent.EXTRA_TEXT);

      // カーソル位置取得
      int curPosition = itIn.getIntExtra("cursor", 0);

      // カーソルが行頭ではない場合、カーソル位置まではそのまま
      if ( curPosition != 0 ) {
     	 targStr.append(origStr.substring(0,curPosition));
      }

      // スチャッ
      targStr.append(schaStr);

      // 残りの文字列を追加する
      targStr.append(origStr.substring(curPosition));

      // 返却しませう
      Intent itOut = new Intent();
      itOut.putExtra(Intent.EXTRA_TEXT, targStr.toString());
      itOut.putExtra("cursor", curPosition + schaStr.length());
      setResult(RESULT_OK, itOut);
      finish();

    }
  }
}