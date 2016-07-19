package com.spurdow.circleviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.spurdow.simplecircleview.SimpleCircleView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleCircleView simpleCircleView = (SimpleCircleView) findViewById(R.id.circleView);

        simpleCircleView.setRadius(150);
    }
}
