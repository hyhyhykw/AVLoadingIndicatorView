package com.wang.avi.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;
import com.wang.avi.indicators.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Jack Wang on 2016/8/5.
 */

public class SampleActivity extends AppCompatActivity{

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        mRecycler= (RecyclerView) findViewById(R.id.recycler);

        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(new RecyclerView.Adapter<IndicatorHolder>() {
            @Override
            public IndicatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView=getLayoutInflater().inflate(R.layout.item_indicator,parent,false);
                return new IndicatorHolder(itemView);
            }

            @Override
            public void onBindViewHolder(IndicatorHolder holder, final int position) {
                try {
                    holder.indicatorView.setIndicator((Indicator) INDICATORS[position].newInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                holder.itemLayout.setOnClickListener(v -> {
                    Intent intent=new Intent(SampleActivity.this,IndicatorActivity.class);
                    intent.putExtra("indicator",INDICATORS[position].getName());
                    startActivity(intent);
                });
            }

            @Override
            public int getItemCount() {
                return INDICATORS.length;
            }
        });
    }

    final static class IndicatorHolder extends RecyclerView.ViewHolder{

        public AVLoadingIndicatorView indicatorView;
        public View itemLayout;

        public IndicatorHolder(View itemView) {
            super(itemView);
            itemLayout= itemView.findViewById(R.id.itemLayout);
            indicatorView= (AVLoadingIndicatorView) itemView.findViewById(R.id.indicator);
        }
    }


    private static final Class<? >[] INDICATORS=new Class<?>[]{
            BallPulseIndicator.class,
            BallGridPulseIndicator.class,
            BallClipRotateIndicator.class,
            BallClipRotatePulseIndicator.class,
            SquareSpinIndicator.class,
            BallClipRotateMultipleIndicator.class,
            BallPulseRiseIndicator.class,
            BallRotateIndicator.class,
            CubeTransitionIndicator.class,
            BallZigZagIndicator.class,
            BallZigZagDeflectIndicator.class,
            BallTrianglePathIndicator.class,
            BallScaleIndicator.class,
            LineScaleIndicator.class,
            LineScalePartyIndicator.class,
            BallScaleMultipleIndicator.class,
            BallPulseSyncIndicator.class,
            BallBeatIndicator.class,
            LineScalePulseOutIndicator.class,
            LineScalePulseOutRapidIndicator.class,
            BallScaleRippleIndicator.class,
            BallScaleRippleMultipleIndicator.class,
            BallSpinFadeLoaderIndicator.class,
            LineSpinFadeLoaderIndicator.class,
            TriangleSkewSpinIndicator.class,
            PacmanIndicator.class,
            BallGridBeatIndicator.class,
            SemiCircleSpinIndicator.class,
            MyCustomIndicator.class
    };



}
