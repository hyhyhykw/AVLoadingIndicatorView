package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import com.wang.avi.Indicator;

import java.util.ArrayList;

/**
 * Created by Jack on 2015/10/19.
 */
public class BallTrianglePathIndicator extends Indicator {

    float[] translateX = new float[3], translateY = new float[3];

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 3; i++) {
            canvas.save();
            canvas.translate(translateX[i], translateY[i]);
            canvas.drawCircle(0, 0, getWidth() / 10f, paint);
            canvas.restore();
        }
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        float startX = getWidth() / 5f;
        float startY = getWidth() / 5f;
        for (int i = 0; i < 3; i++) {
            final int index = i;
            ValueAnimator translateXAnim = ValueAnimator.ofFloat(getWidth() / 2f, getWidth() - startX, startX, getWidth() / 2f);
            if (i == 1) {
                translateXAnim = ValueAnimator.ofFloat(getWidth() - startX, startX, getWidth() / 2f, getWidth() - startX);
            } else if (i == 2) {
                translateXAnim = ValueAnimator.ofFloat(startX, getWidth() / 2f, getWidth() - startX, startX);
            }
            ValueAnimator translateYAnim = ValueAnimator.ofFloat(startY, getHeight() - startY, getHeight() - startY, startY);
            if (i == 1) {
                translateYAnim = ValueAnimator.ofFloat(getHeight() - startY, getHeight() - startY, startY, getHeight() - startY);
            } else if (i == 2) {
                translateYAnim = ValueAnimator.ofFloat(getHeight() - startY, startY, getHeight() - startY, getHeight() - startY);
            }

            translateXAnim.setDuration(2000);
            translateXAnim.setInterpolator(new LinearInterpolator());
            translateXAnim.setRepeatCount(-1);
            addUpdateListener(translateXAnim, animation -> {
                translateX[index] = (float) animation.getAnimatedValue();
                postInvalidate();
            });

            translateYAnim.setDuration(2000);
            translateYAnim.setInterpolator(new LinearInterpolator());
            translateYAnim.setRepeatCount(-1);
            addUpdateListener(translateYAnim, animation -> {
                translateY[index] = (float) animation.getAnimatedValue();
                postInvalidate();
            });

            animators.add(translateXAnim);
            animators.add(translateYAnim);
        }
        return animators;
    }


}
