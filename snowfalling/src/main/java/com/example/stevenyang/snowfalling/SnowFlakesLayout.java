package com.example.stevenyang.snowfalling;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by User on 2016/12/6.
 */
public class SnowFlakesLayout extends RelativeLayout {
    Context context;
    float height;
    float width;
    int animateDuration =10000;
    int wholeAnimateTiming = 300000;
    int generateSnowTiming = 1000;
    int imageResourceID;
    int snowMaxSize = 40;
    int snowMinSize = 1;
    boolean shouldRandomSnowSize = false;
    boolean enableRandomCurving = false;
    boolean enableAlphaFade = false;
    final int snowFlakeYInitializePosition = -30;
    Bitmap snowFlakesBitmap;
    CountDownTimer mainCountdownSnowTimer;
    Random generator = new Random();
    Handler mHandler = new Handler();
    public SnowFlakesLayout(Context context) {
        super(context);
        this.context = context;

    }

    public SnowFlakesLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SnowFlakesLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SnowFlakesLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }


    public void setImageBitmap(Bitmap snowFlakesBitmap) {
        this.snowFlakesBitmap = snowFlakesBitmap;
    }

    public void setEnableAlphaFade(boolean enableAlphaFade) {
        this.enableAlphaFade = enableAlphaFade;
    }

    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }

    public void setWholeAnimateTiming(int wholeAnimateTiming) {
        this.wholeAnimateTiming = wholeAnimateTiming;
    }

    public void setAnimateDuration(int animateDuration) {
        this.animateDuration = animateDuration;
    }

    public void setGenerateSnowTiming(int generateSnowTiming) {
        this.generateSnowTiming = generateSnowTiming;
    }
    public void setRandomSnowSizeRange(int max, int min){
        shouldRandomSnowSize = true;
        this.snowMaxSize = max;
        this.snowMinSize = min;
    }

    public void setEnableRandomCurving(boolean enableRandomCurving) {
        this.enableRandomCurving = enableRandomCurving;
    }

    public void init() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;
        imageResourceID = R.drawable.snow_flakes_pic;
     }

    private void showSnow(){
        final ImageView snowAnimationView = new ImageView(context);
        snowAnimationView.setClickable(false);
        if(snowFlakesBitmap!=null){
            snowAnimationView.setImageBitmap(snowFlakesBitmap);
        }else{
            snowAnimationView.setImageResource(imageResourceID);
        }
        int flakeWidth = snowMaxSize;
        int flakeHeight = snowMaxSize;
        if (shouldRandomSnowSize) {
            int i = generator.nextInt(snowMaxSize) + snowMinSize;
            flakeHeight = i;
            flakeWidth = i;
        }
        RelativeLayout.LayoutParams snowParam = new RelativeLayout.LayoutParams(flakeWidth, flakeHeight);
        int i2 = generator.nextInt((int) width) + 1;
        snowParam.setMargins((int) (width - flakeWidth - i2), 0, 0, 0);
        this.addView(snowAnimationView, snowParam);
        AnimationSet animationSet = new AnimationSet(false);
        TranslateAnimation scale = new TranslateAnimation(Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF,snowFlakeYInitializePosition, height);
        scale.setDuration(animateDuration);
        animationSet.addAnimation(scale);

        if (enableRandomCurving) {
            int i3 = generator.nextInt(180) - 90;
            RotateAnimation r = new RotateAnimation(0f, i3);
            r.setStartOffset(animateDuration / 10);
            r.setDuration(animateDuration);
            animationSet.addAnimation(r);
        }


        if (enableAlphaFade) {
            AlphaAnimation animation = new AlphaAnimation(1.0f, 0.3f);
            animation.setDuration(animateDuration);
            animationSet.addAnimation(animation);
        }

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                snowAnimationView.setVisibility(View.GONE);
                SnowFlakesLayout.this.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SnowFlakesLayout.this.removeView(snowAnimationView);
                    }
                }, 500);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        snowAnimationView.setTag(R.id.tag_countdown_timer, animationSet);
        snowAnimationView.setAnimation(animationSet);
        animationSet.startNow();
    }

    public void startSnowing(){
        mainCountdownSnowTimer = new CountDownTimer(wholeAnimateTiming, generateSnowTiming){

            @Override
            public void onTick(long millisUntilFinished) {
                showSnow();
            }

            @Override
            public void onFinish() {
                stopSnowing();

            }
        }.start();
    }

    public void stopSnowing(){
        if(mainCountdownSnowTimer!=null)
        mainCountdownSnowTimer.cancel();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                int count = getChildCount();
                for (int i = 0; i < count; i++) {
                    View view = getChildAt(i);
                    if (view != null) {
                        view.clearAnimation();
                    }

                }
                removeAllViews();
            }
        });
    }

}
