package com.audacity.ridemate.SplashScreen;

import android.animation.Animator;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Prince on 5/30/17.
 */

public class AnimationEndListener implements Animator.AnimatorListener{

    private ViewAnimator viewAnimator;
    private View view;

    public AnimationEndListener(@Nullable ViewAnimator viewAnimator){
        this.viewAnimator = viewAnimator;
    }



    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        if(viewAnimator!=null){
            viewAnimator.playAnimation();
        }else{
            AnimationChainCompleteObserver.GetInstance().notifyObservers();
        }
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}
