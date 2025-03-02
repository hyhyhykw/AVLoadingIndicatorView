package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.animation.LinearInterpolator;

import com.wang.avi.Indicator;

import java.util.ArrayList;

/**
 * Created by Jack on 2015/10/20.
 */
public class TriangleSkewSpinIndicator extends Indicator {

    private float rotateX;
    private float rotateY;

    private final Camera mCamera;
    private final Matrix mMatrix;

    public TriangleSkewSpinIndicator() {
        mCamera = new Camera();
        mMatrix = new Matrix();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {


        mMatrix.reset();
        mCamera.save();
        mCamera.rotateX(rotateX);
        mCamera.rotateY(rotateY);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        mMatrix.preTranslate(-centerX(), -centerY());
        mMatrix.postTranslate(centerX(), centerY());
        canvas.concat(mMatrix);

        Path path = new Path();
        path.moveTo(getWidth() / 5f, getHeight() * 4f / 5);
        path.lineTo(getWidth() * 4f / 5, getHeight() * 4f / 5);
        path.lineTo(getWidth() / 2f, getHeight() / 5f);
        path.close();
        canvas.drawPath(path, paint);
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        ValueAnimator animator = ValueAnimator.ofFloat(0, 180, 180, 0, 0);
        addUpdateListener(animator, animation -> {
            rotateX = (float) animation.getAnimatedValue();
            postInvalidate();
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);
        animator.setDuration(2500);

        ValueAnimator animator1 = ValueAnimator.ofFloat(0, 0, 180, 180, 0);
        addUpdateListener(animator1, animation -> {
            rotateY = (float) animation.getAnimatedValue();
            postInvalidate();
        });
        animator1.setInterpolator(new LinearInterpolator());
        animator1.setRepeatCount(-1);
        animator1.setDuration(2500);

        animators.add(animator);
        animators.add(animator1);
        return animators;
    }

}
