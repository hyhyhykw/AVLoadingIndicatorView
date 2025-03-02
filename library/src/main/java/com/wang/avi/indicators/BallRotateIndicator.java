package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.wang.avi.Indicator;

import java.util.ArrayList;

/**
 * Created by Jack on 2015/10/17.
 */
public class BallRotateIndicator extends Indicator {

    float scaleFloat = 0.5f;

    float degress;

    public BallRotateIndicator() {
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float radius = getWidth() / 10f;
        float x = getWidth() / 2f;
        float y = getHeight() / 2f;

        /*mMatrix.preTranslate(-centerX(), -centerY());
        mMatrix.preRotate(degress,centerX(),centerY());
        mMatrix.postTranslate(centerX(), centerY());
        canvas.concat(mMatrix);*/

        canvas.rotate(degress, centerX(), centerY());

        canvas.save();
        canvas.translate(x - radius * 2 - radius, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(x + radius * 2 + radius, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.restore();
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(0.5f, 1, 0.5f);
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim, animation -> {
            scaleFloat = (float) animation.getAnimatedValue();
            postInvalidate();
        });

        ValueAnimator rotateAnim = ValueAnimator.ofFloat(0, 180, 360);
        addUpdateListener(rotateAnim, animation -> {
            degress = (float) animation.getAnimatedValue();
            postInvalidate();
        });
        rotateAnim.setDuration(1000);
        rotateAnim.setRepeatCount(-1);

        animators.add(scaleAnim);
        animators.add(rotateAnim);
        return animators;
    }

}
