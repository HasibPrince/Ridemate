package com.audacity.ridemate.SplashScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prince on 5/30/17.
 */

public class AnimationChainCompleteObserver {
    private List<AnimationChainCompletedListener> observers = new ArrayList<>();
    private static AnimationChainCompleteObserver observer = new AnimationChainCompleteObserver();

    public static AnimationChainCompleteObserver GetInstance(){
        return observer;
    }

    private AnimationChainCompleteObserver(){

    }

    public void addObserver(AnimationChainCompletedListener observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        for(AnimationChainCompletedListener observer : observers){
            observer.onAnimationCompleted();
        }
    }
}
