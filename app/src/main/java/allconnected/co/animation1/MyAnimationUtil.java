package allconnected.co.animation1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by michael on 16/12/1.
 */

public class MyAnimationUtil {

    private static Handler mHandler = new Handler();

    private static float mCount;

    public static ObjectAnimator getCountdownAnimator(View view) {
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1.03f),
                Keyframe.ofFloat(.1f, .98f),
                Keyframe.ofFloat(.2f, 1.03f),
                Keyframe.ofFloat(.3f, .98f),
                Keyframe.ofFloat(.4f, .98f),
                Keyframe.ofFloat(.5f, .98f),
                Keyframe.ofFloat(.6f, .98f),
                Keyframe.ofFloat(.7f, .98f),
                Keyframe.ofFloat(.8f, .98f),
                Keyframe.ofFloat(.9f, .98f),
                Keyframe.ofFloat(1f, 0f)
        );
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1.03f),
                Keyframe.ofFloat(.1f, .98f),
                Keyframe.ofFloat(.2f, 1.03f),
                Keyframe.ofFloat(.3f, .98f),
                Keyframe.ofFloat(.4f, .98f),
                Keyframe.ofFloat(.5f, .98f),
                Keyframe.ofFloat(.6f, .98f),
                Keyframe.ofFloat(.7f, .98f),
                Keyframe.ofFloat(.8f, .98f),
                Keyframe.ofFloat(.9f, .98f),
                Keyframe.ofFloat(1f, 0f)
        );
        return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY).setDuration(1900);
    }


    public static ObjectAnimator getTranslationAnimator(View view, float screenWidth) {
        float deltaX = screenWidth;
        float deltaY = -screenWidth / 2;

        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.10f, deltaX * 0.1f),
                Keyframe.ofFloat(.26f, deltaX * 0.3f),
                Keyframe.ofFloat(.42f, deltaX * 0.4f),
                Keyframe.ofFloat(.58f, deltaX * 0.6f),
                Keyframe.ofFloat(.74f, deltaX * 0.8f),
                Keyframe.ofFloat(.90f, deltaX * 0.9f),
                Keyframe.ofFloat(1f, deltaX)
        );

        PropertyValuesHolder pvhTranslateY = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_Y,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.10f, deltaY * 0.1f),
                Keyframe.ofFloat(.26f, deltaY * 0.4f),
                Keyframe.ofFloat(.42f, deltaY * 0.55f),
                Keyframe.ofFloat(.58f, deltaY * 0.7f),
                Keyframe.ofFloat(.74f, deltaY * 0.95f),
                Keyframe.ofFloat(.90f, deltaY),
                Keyframe.ofFloat(1f, deltaY)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhTranslateX, pvhTranslateY).
                setDuration(1000);
    }


    public static ObjectAnimator getBoundAnimator(View view) {

        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, .95f),
                Keyframe.ofFloat(.1f, .97f),
                Keyframe.ofFloat(.2f, .97f),
                Keyframe.ofFloat(.3f, .97f),
                Keyframe.ofFloat(.4f, .94f),
                Keyframe.ofFloat(.5f, .94f),
                Keyframe.ofFloat(.6f, .94f),
                Keyframe.ofFloat(.7f, .94f),
                Keyframe.ofFloat(.8f, .97f),
                Keyframe.ofFloat(.9f, .97f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, .95f),
                Keyframe.ofFloat(.1f, .97f),
                Keyframe.ofFloat(.2f, .97f),
                Keyframe.ofFloat(.3f, .97f),
                Keyframe.ofFloat(.4f, .94f),
                Keyframe.ofFloat(.5f, .94f),
                Keyframe.ofFloat(.6f, .94f),
                Keyframe.ofFloat(.7f, .94f),
                Keyframe.ofFloat(.8f, .97f),
                Keyframe.ofFloat(.9f, .97f),
                Keyframe.ofFloat(1f, 1f)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY).
                setDuration(400);
    }


    public static MyCountdownTimer startCountDownAni(TextView view, Animator myanimator, int ticks) {
        MyCountdownTimer myCountdownTimer = new MyCountdownTimer(view, myanimator, ticks);
        myCountdownTimer.start();
        return myCountdownTimer;
    }

    public static void stopCountDownAni(CountDownTimer timer) {
        timer.cancel();
    }


    private static class MyCountdownTimer extends CountDownTimer {
        private final TextView mView;
        private final Animator mAnimator;
        private int count;

        public MyCountdownTimer(TextView view, Animator animator, int ticks) {
            super(ticks * 2000 + 3000, 2000);
            this.count = ticks + 1;
            this.mView = view;
            this.mAnimator = animator;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (count > 0) {
                count--;
            }
            mView.setText(String.valueOf(count));
            mAnimator.start();
        }

        @Override
        public void onFinish() {
            mView.animate().scaleX(1f).scaleY(1f);

        }
    }

    public static Timer showRatingAni(final RatingBar ratingBar) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        float rating = mCount += 0.5;
                        if (rating > 5) {
                            mCount = rating = 0;
                        }
                        ratingBar.setRating(rating);
                    }
                });

            }
        }, 0, 500);

        return timer;

    }


    public static void stopRatingAni(Timer timer) {
        timer.cancel();
    }

    public static void startEggAni(View lfet, View right, View bird) {
        Runnable animRunnable = new EggRunnable(lfet, right, bird);
        mHandler.post(animRunnable);
    }

    private static int mCount2;

    private static void startAppWallAnimSet(final View leftEggView, final View rightEggView, final View birdView) {
        final AnimatorSet shakeAnimSet = new AnimatorSet();
        final AnimatorSet openAnimSet = new AnimatorSet();
        final AnimatorSet closeAnimSet = new AnimatorSet();

        leftEggView.setPivotX(0.5f);
        leftEggView.setPivotY(0.5f);
        rightEggView.setPivotX(0.5f);
        rightEggView.setPivotY(0.5f);

        //Shake egg
        ObjectAnimator shakeLeft = ObjectAnimator.ofFloat(leftEggView, "rotation", 0, 3, 6, 3, 0, -3, -6, -3, 0);
        ObjectAnimator shakeRight = ObjectAnimator.ofFloat(rightEggView, "rotation", 0, 3, 6, 3, 0, -3, -6, -3, 0);
        shakeLeft.setRepeatCount(3);
        shakeRight.setRepeatCount(3);
        shakeAnimSet.setDuration(240);
        shakeAnimSet.playTogether(shakeLeft, shakeRight);

        // Open egg with bird alpha visible
        ObjectAnimator openLeft = ObjectAnimator.ofFloat(leftEggView, "rotation", 0, -20, -40, -50, -60, -50, -60, -50);
        ObjectAnimator openRight = ObjectAnimator.ofFloat(rightEggView, "rotation", 0, 20, 40, 50, 60, 50, 60, 50);
        ObjectAnimator birdAlphaVisibleAnim = ObjectAnimator.ofFloat(birdView, "alpha", 0, 1);
        ObjectAnimator translateAnim = ObjectAnimator.ofFloat(birdView, "translationY", 0, 0, 0, 0, 0, 0, 0, 0, 5, -5, 5, 0);
        openAnimSet.setDuration(1200);
        openAnimSet.playTogether(openLeft, openRight, birdAlphaVisibleAnim, translateAnim);

        // Close egg with bird alpha invisible
        ObjectAnimator closeLeft = ObjectAnimator.ofFloat(leftEggView, "rotation", -50, -40, -30, -20, 0);
        ObjectAnimator closeRight = ObjectAnimator.ofFloat(rightEggView, "rotation", 50, 40, 30, 20, 0);
        ObjectAnimator birdAlphaGoneAnim = ObjectAnimator.ofFloat(birdView, "alpha", 1, 0);
        closeAnimSet.setDuration(600);
        closeAnimSet.playTogether(closeLeft, closeRight, birdAlphaGoneAnim);

        shakeAnimSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {

                if (mCount2 == 0) {
                    mCount2 += 1;
                    super.onAnimationEnd(animation);
                    shakeAnimSet.setStartDelay(300);
                    shakeAnimSet.start();
                } else if (mCount2 == 1) {
                    // After twice shaking, start open egg animator
                    openAnimSet.setStartDelay(200);
                    openAnimSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            // Match the open animation delay
                            birdView.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    birdView.setVisibility(View.VISIBLE);
                                }
                            }, 300);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            closeAnimSet.setStartDelay(500);
                            closeAnimSet.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    mCount2 = 0;
                                    birdView.setVisibility(View.INVISIBLE);
                                    closeAnimSet.removeAllListeners();
                                    shakeAnimSet.setStartDelay(1000);
                                    shakeAnimSet.start();
                                }
                            });
                            openAnimSet.removeAllListeners();
                            closeAnimSet.start();
                        }
                    });
                    openAnimSet.start();
                    return;
                }
            }
        });
        shakeAnimSet.start();
    }


    private static class EggRunnable implements Runnable {

        private View leftEggIv, rightEggIv, birdIv;

        public EggRunnable(View left, View right, View bird) {

            this.leftEggIv = left;
            this.rightEggIv = right;
            this.birdIv = bird;

        }

        @Override
        public void run() {
            startAppWallAnimSet(leftEggIv, rightEggIv, birdIv);

        }
    }

}
