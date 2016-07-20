package com.spurdow.circleviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.spurdow.simplecircleview.SimpleCircleView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar mSeekBar;
    SimpleCircleView mSimpleCircleView;
    static final int DEFAULT_PROGRESS = 25;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSimpleCircleView = (SimpleCircleView) findViewById(R.id.circleView);
        mSimpleCircleView .setRadius(DEFAULT_PROGRESS);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);
        mSeekBar.setProgress(DEFAULT_PROGRESS);
        mSeekBar.setMax(1000);


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mSimpleCircleView.setRadius(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
