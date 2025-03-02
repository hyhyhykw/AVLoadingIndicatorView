package com.wang.avi.sample;

import android.os.Bundle;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Jack Wang on 2016/8/5.
 */

public class IndicatorActivity extends AppCompatActivity{

    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);

        String indicator=getIntent().getStringExtra("indicator");
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        try {
            avi.setIndicator((Indicator) Class.forName(indicator).newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void hideClick(View view) {
        avi.hide();
        // or avi.smoothToHide();
    }

    public void showClick(View view) {
        avi.show();
        // or avi.smoothToShow();
    }
}
