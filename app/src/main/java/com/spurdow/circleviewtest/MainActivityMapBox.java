package com.spurdow.circleviewtest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.MarkerView;
import com.mapbox.mapboxsdk.annotations.MarkerViewManager;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.spurdow.simplecircleview.SimpleCircleView;

/**
 * Created by android on 7/20/16.
 */
public class MainActivityMapBox extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, MapView.OnMapChangedListener, OnMapReadyCallback, MapboxMap.OnCameraChangeListener {

    SeekBar mSeekBar;
    CircleMarkerView mCircleMarkerView;
    MapView mMapView;
    MapboxMap mMap;
    MarkerViewManager mMarkerViewManager;
    private float current_progress =25;
    static final int DEFAULT_PROGRESS = 25;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapboxAccountManager.start(this , getString(R.string.mapbox_access_token));
        setContentView(R.layout.main_activity_mapbox);
//        mSimpleCircleView = (SimpleCircleView) findViewById(R.id.circleView);
//        mSimpleCircleView .setRadius(DEFAULT_PROGRESS);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);
        mSeekBar.setProgress(DEFAULT_PROGRESS);
        mSeekBar.setMax(1000);

        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.addOnMapChangedListener(this);

        mMapView.getMapAsync(this);





    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        mSimpleCircleView.setRadius(progress);

        if(mMap != null) {
            if(mCircleMarkerView != null){
//                mCircleMarkerView.setRadius(progress);
//                mCircleMarkerView.setPosition(new LatLng(10.308498, 123.885932));
                mMap.removeMarker(mCircleMarkerView);
                CircleMarkerViewOptions options = new CircleMarkerViewOptions();
                options.radius(progress);
                options.color(R.color.colorAccent);
                options.position(new LatLng(10.308498, 123.885932));
                mCircleMarkerView = (CircleMarkerView) mMap.addMarker(options);

            }


        }
        current_progress = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onMapChanged(int change) {

    }


    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        mMap = mapboxMap;
        CircleMarkerViewOptions options = new CircleMarkerViewOptions();
        options.radius(25);
        options.color(Color.parseColor("#FF4081"));
        options.position(new LatLng(10.308498, 123.885932));
        mCircleMarkerView = (CircleMarkerView) mMap.addMarker(options);
        mMarkerViewManager = mMap.getMarkerViewManager();
        mMap.setOnCameraChangeListener(this);
        mMarkerViewManager.addMarkerViewAdapter(new CircleMarkerAdapter(this , mapboxMap));

    }

    @Override
    public void onCameraChange(CameraPosition position) {
        if(mMap != null) {
            if(mCircleMarkerView != null){
//                mCircleMarkerView.setRadius(progress);
//                mCircleMarkerView.setPosition(new LatLng(10.308498, 123.885932));
                mMap.removeMarker(mCircleMarkerView);
                CircleMarkerViewOptions options = new CircleMarkerViewOptions();
                options.radius(current_progress);
                options.color(R.color.colorAccent);
                options.position(new LatLng(10.308498, 123.885932));
                mCircleMarkerView = (CircleMarkerView) mMap.addMarker(options);

            }


        }
    }


    private static class CircleMarkerAdapter extends MapboxMap.MarkerViewAdapter<CircleMarkerView> implements MapboxMap.OnCameraChangeListener{
        private static final String TAG = CircleMarkerAdapter.class.getSimpleName();
        private LayoutInflater inflater;
        private MapboxMap mapboxMap;
        /**
         * Create an instance of MarkerViewAdapter.
         *
         * @param context the context associated to a MapView
         */
        public CircleMarkerAdapter(@NonNull Context context, @NonNull MapboxMap mapboxMap) {
            super(context);
            this.inflater = LayoutInflater.from(context);
            this.mapboxMap = mapboxMap;
            this.mapboxMap.setOnCameraChangeListener(this);

        }

        @Nullable
        @Override
        public View getView(@NonNull CircleMarkerView marker, @NonNull View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.circle_layout, parent, false);
                viewHolder.mSimpleCircleView = (SimpleCircleView) convertView.findViewById(R.id.circleView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.mSimpleCircleView.setFillColor(marker.getColor());
            viewHolder.mSimpleCircleView.setRadius(marker.getRadius());

//            viewHolder.mCircleView.setPosition(marker.getPosition());
//            viewHolder.mCircleView.setMap(mapboxMap);


            Log.w(TAG , "Updating CircleView " );

            return convertView;
        }

        @Override
        public void onCameraChange(CameraPosition position) {

            Log.w(TAG , "onCameraChange");
        }

        @Override
        public boolean prepareViewForReuse(@NonNull MarkerView marker, @NonNull View convertView) {
            this.mapboxMap.setOnCameraChangeListener(null);
            return super.prepareViewForReuse(marker, convertView);
        }



        private static class ViewHolder{
            SimpleCircleView mSimpleCircleView;
        }
    }




    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}
