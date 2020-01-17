package com.ethiopian.amharic.professional;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Help extends Activity
{
  private static final int SWIPE_MIN_DISTANCE = 120;
  private static final int SWIPE_THRESHOLD_VELOCITY = 200;
  private Context ctx;
  private final GestureDetector detector;
  Integer[] imageIDs;
  private Animation.AnimationListener mAnimationListener;
  ImageView[] mImageView;
  ViewFlipper mViewFlipper;

  public Help()
  {
    Integer[] arrayOfInteger = new Integer[6];
    arrayOfInteger[0] = Integer.valueOf(R.drawable.hahu_0);
    arrayOfInteger[1] = Integer.valueOf(R.drawable.hahu_1);
    arrayOfInteger[2] = Integer.valueOf(R.drawable.hahu_2);
    arrayOfInteger[3] = Integer.valueOf(R.drawable.hahu_3);
    arrayOfInteger[4] = Integer.valueOf(R.drawable.hahu_4);
    arrayOfInteger[5] = Integer.valueOf(R.drawable.hahu_5);
   
    this.imageIDs = arrayOfInteger;
    this.detector = new GestureDetector(new SwipeGestureDetector());
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_help);
    this.ctx = this;
    this.mImageView = new ImageView[this.imageIDs.length];
    this.mViewFlipper = ((ViewFlipper)findViewById(R.id.viewFlipperHelp));
    for (int i = 0; ; i++)
    {
      if (i >= this.imageIDs.length)
      {
        this.mViewFlipper.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            Help.this.detector.onTouchEvent(paramAnonymousMotionEvent);
            return true;
          }
        });
        this.mAnimationListener = new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymousAnimation)
          {
          }

          public void onAnimationRepeat(Animation paramAnonymousAnimation)
          {
          }

          public void onAnimationStart(Animation paramAnonymousAnimation)
          {
          }
        };
        this.mViewFlipper.setAutoStart(true);
        this.mViewFlipper.setFlipInterval(6000);
        this.mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this.ctx, R.anim.slides_in_from_the_left));
        this.mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this.ctx, R.anim.slides_out_to_the_right));
        this.mViewFlipper.startFlipping();
        return;
      }
      this.mImageView[i] = new ImageView(this.ctx);
      this.mImageView[i].setImageResource(this.imageIDs[i].intValue());
      this.mImageView[i].setScaleType(ImageView.ScaleType.FIT_XY);
      this.mImageView[i].setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
      this.mViewFlipper.addView(this.mImageView[i]);
    }
  }

  class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener
  {
    SwipeGestureDetector()
    {
    }

    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      try
      {
        if ((paramMotionEvent1.getX() - paramMotionEvent2.getX() > 120.0F) && (Math.abs(paramFloat1) > 200.0F))
        {
          Help.this.mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(Help.this.ctx, R.anim.slides_in_from_the_left));
          Help.this.mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(Help.this.ctx, R.anim.slides_out_to_the_right));
          Help.this.mViewFlipper.getInAnimation().setAnimationListener(Help.this.mAnimationListener);
          Help.this.mViewFlipper.showNext();
          return true;
        }
        if ((paramMotionEvent2.getX() - paramMotionEvent1.getX() > 120.0F) && (Math.abs(paramFloat1) > 200.0F))
        {
          Help.this.mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(Help.this.ctx, R.anim.slides_in_from_the_left));
          Help.this.mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(Help.this.ctx, R.anim.slides_out_to_the_right));
          Help.this.mViewFlipper.getInAnimation().setAnimationListener(Help.this.mAnimationListener);
          Help.this.mViewFlipper.showPrevious();
          return true;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return false;
    }
  }
}

