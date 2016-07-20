package com.spurdow.circleviewtest;

import com.mapbox.mapboxsdk.annotations.BaseMarkerViewOptions;
import com.mapbox.mapboxsdk.annotations.MarkerView;
import com.mapbox.mapboxsdk.maps.MapboxMap;

public class CircleMarkerView extends MarkerView {

    private int color;
    private float radius;


    public CircleMarkerView(BaseMarkerViewOptions baseMarkerViewOptions, int color , float radius) {
        super(baseMarkerViewOptions);
        this.color = color;
        this.radius = radius;

    }

    public int getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }

    public void setColor(int color) {
        this.color = color;
        MapboxMap map = getMapboxMap();
        if (map != null) {
            map.updateMarker(this);
            map.getMarkerViewManager().update();
        }
    }

    public void setRadius(float radius) {
        this.radius = radius;
        MapboxMap map = getMapboxMap();
        if (map != null) {
            map.updateMarker(this);
            map.getMarkerViewManager().update();
        }
    }
}