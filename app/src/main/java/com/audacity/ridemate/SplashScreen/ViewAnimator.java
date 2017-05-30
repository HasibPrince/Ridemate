package com.audacity.ridemate.SplashScreen;

import android.support.annotation.NonNull;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by Prince on 5/30/17.
 */

public class ViewAnimator {
    private YoYo.AnimationComposer animationComposer;
    private View view;

    public ViewAnimator(Techniques techniques, long duration, @NonNull View view,@NonNull AnimationEndListener nextAnimation){
        this.animationComposer = YoYo.with(techniques).duration(duration).withListener(nextAnimation);
        this.view = view;

    }

    public void playAnimation(){
        if(view.getVisibility()!= View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }
        animationComposer.playOn(view);
    }
}
