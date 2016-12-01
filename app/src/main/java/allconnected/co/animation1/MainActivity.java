package allconnected.co.animation1;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView scaleTv = (TextView) findViewById(R.id.tv_scale);
        final TextView transTv = (TextView) findViewById(R.id.tv_trans);
        final ObjectAnimator countdownAnimator = MyAnimationUtil.getCountdownAnimator(scaleTv);
        float screenWidth=getResources().getDisplayMetrics().widthPixels;
      final  ObjectAnimator translationAnimator = MyAnimationUtil.getTranslationAnimator(transTv, screenWidth);
        Button scaleButton = (Button) findViewById(R.id.btn_scale);
        Button translateButton = (Button) findViewById(R.id.btn_translate);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translationAnimator.start();

            }
        });
        scaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ticks=15;
                MyAnimationUtil.startCountDownAni(scaleTv,countdownAnimator,ticks);

            }
        });

    }
}
