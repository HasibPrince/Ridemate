package com.audacity.ridemate.SplashScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.audacity.ridemate.ConfirmationPage.MainActivity;
import com.audacity.ridemate.R;
import com.daimajia.androidanimations.library.Techniques;

public class SplashScreen extends AppCompatActivity implements AnimationChainCompletedListener {

    private ImageView iconCenter;
    private ImageView iconBottom;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initUI();
        AnimationChainCompleteObserver.GetInstance().addObserver(this);
    }

    private void initUI() {
        iconCenter = (ImageView)findViewById(R.id.icon_center);
        iconBottom = (ImageView)findViewById(R.id.icon_bottom);
        title = (TextView) findViewById(R.id.title);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startSplashScreenAnimation();
    }

    private void startSplashScreenAnimation() {
        new ViewAnimator(Techniques.Tada,1000,iconCenter,new AnimationEndListener(getCenterIconViewAnimator())).playAnimation();
    }

    private ViewAnimator getCenterIconViewAnimator() {
        return  new ViewAnimator(Techniques.Tada,1000,iconCenter,new AnimationEndListener(getTitleViewAnimator()));

    }

    private ViewAnimator getTitleViewAnimator() {
        return new ViewAnimator(Techniques.SlideInUp,1500,title,new AnimationEndListener(getBottomIconAnimator()));
    }

    private ViewAnimator getBottomIconAnimator() {
        return new ViewAnimator(Techniques.FlipInY, 1500, iconBottom, new AnimationEndListener(null));
    }

    @Override
    public void onAnimationCompleted() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
