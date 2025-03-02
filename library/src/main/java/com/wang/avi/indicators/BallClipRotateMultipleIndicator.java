package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.wang.avi.Indicator;

import java.util.ArrayList;

/**
 * Created by Jack on 2015/10/17.
 */
public class BallClipRotateMultipleIndicator extends Indicator {

    float scaleFloat = 1, degrees;


    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        float circleSpacing = 12;
        float x = getWidth() / 2f;
        float y = getHeight() / 2f;

        canvas.save();

        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(degrees);

        //draw two big arc
        float[] bStartAngles = new float[]{135, -45};
        for (int i = 0; i < 2; i++) {
            RectF rectF = new RectF(-x + circleSpacing, -y + circleSpacing, x - circleSpacing, y - circleSpacing);
            canvas.drawArc(rectF, bStartAngles[i], 90, false, paint);
        }

        canvas.restore();
        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(-degrees);
        //draw two small arc
        float[] sStartAngles = new float[]{225, 45};
        for (int i = 0; i < 2; i++) {
            RectF rectF = new RectF(-x / 1.8f + circleSpacing, -y / 1.8f + circleSpacing, x / 1.8f - circleSpacing, y / 1.8f - circleSpacing);
            canvas.drawArc(rectF, sStartAngles[i], 90, false, paint);
        }
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.6f, 1);
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim, animation -> {
            scaleFloat = (float) animation.getAnimatedValue();
            postInvalidate();
        });

        ValueAnimator rotateAnim = ValueAnimator.ofFloat(0, 180, 360);
        rotateAnim.setDuration(1000);
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
