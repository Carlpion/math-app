package com.calculator.equations;

import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;

public class Utils {
    static boolean canAddChar(String mainCalculation) {
        if (mainCalculation.length() > 0) {
            String lastChar = mainCalculation.substring(mainCalculation.length() - 1).toLowerCase();
            return lastChar.equals("x") || lastChar.equals("÷") || lastChar.equals("-") || lastChar.equals("+");
        }
        return true;
    }

    static void scaleView(View v, float startScale, float endScale, int duration) {
        Animation anim = new ScaleAnimation(
                startScale, endScale, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(duration);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation anim2 = new ScaleAnimation(
                        endScale, 1f, // Start and end values for the X axis scaling
                        endScale, 1f, // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
                anim2.setFillAfter(true); // Needed to keep the result of the animation
                anim2.setDuration(duration);
                v.startAnimation(anim2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(anim);
    }

    static void rotateView(TextView v, TextView v2, int duration) {
        Animation anim = new RotateAnimation(
                0, -180, // Start and end values for the X axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0f); // Pivot point of Y scaling
        anim.setDuration(duration);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        TranslateAnimation translateAnimation = new TranslateAnimation(0f,-1000f,0f,0f);
        translateAnimation.setDuration(duration);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setText("");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0f,1000f,0f,0f);
        translateAnimation2.setDuration(duration);

        translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v2.setText("");

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        v.startAnimation(translateAnimation);
        v2.startAnimation(translateAnimation2);
    }

}
