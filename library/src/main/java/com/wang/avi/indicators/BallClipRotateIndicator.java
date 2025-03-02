package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.wang.avi.Indicator;

import java.util.ArrayList;

/**
 * Created by Jack on 2015/10/16.
 */
public class BallClipRotateIndicator extends Indicator {

    float scaleFloat = 1, degrees;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

        float circleSpacing = 12;
        float x = (getWidth()) / 2f;
        float y = (getHeight()) / 2f;
        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(degrees);
        RectF rectF = new RectF(-x + circleSpacing, -y + circleSpacing, 0 + x - circleSpacing, 0 + y - circleSpacing);
        canvas.drawArc(rectF, -45, 270, false, paint);
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.6f, 0.5f, 1);
        scaleAnim.setDuration(750);
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim, animation -> {
            scaleFloat = (float) animation.getAnimatedValue();
            postInvalidate();
        });
        ValueAnimator rotateAnim = ValueAnimator.ofFloat(0, 180, 360);
        rotateAnim.setDuration(750);
        rotateAnim.setRepeatCount(-1);
        addUpdateListener(rotateAnim, animation -> {
            degrees = (float) animation.getAnimatedValue();
            postInvalidate();
        });
        animators.add(scaleAnim);
        animators.add(rotateAnim);
        return animators;
    }

}
