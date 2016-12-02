package allconnected.co.animation1;

import android.animation.Animator;
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


}
