package com.example.gem.tv;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.app.BackgroundManager;
import android.util.DisplayMetrics;

/**
 * Created by gem on 6/28/18.
 */

public class SimpleBackgroundManager {

  private static  final String TAG = SimpleBackgroundManager.class.getSimpleName();

  private final int DEFAULT_BACKGROUND_RES_ID = R.drawable.background_first;
  private static Drawable mDefaultBackground;

  private Activity mActivity;
  private BackgroundManager mBackgroundManager;

  public SimpleBackgroundManager(Activity mActivity) {
    this.mActivity = mActivity;
    mDefaultBackground = mActivity.getDrawable(DEFAULT_BACKGROUND_RES_ID);
    mBackgroundManager = BackgroundManager.getInstance(mActivity);
    mBackgroundManager.attach(mActivity.getWindow());
    mActivity.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
  }

  public void updateBackground(Drawable drawable) {
    mBackgroundManager.setDrawable(drawable);
  }

  public void clearBackground() {
    mBackgroundManager.setDrawable(mDefaultBackground);
  }
}
