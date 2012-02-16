package com.byeonggon.memap;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class Memap extends MapActivity {
	private MapView map;
	private MapController controller;
	MyLocationOverlay2 myLoc;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        map = (MapView) findViewById(R.id.mapview);
		controller = map.getController();
		map.setSatellite(false);
		map.setBuiltInZoomControls(true);
        myLoc = new MyLocationOverlay2(this,map);
        
        myLoc.enableMyLocation();
		myLoc.runOnFirstFix(new Runnable() {
			public void run() {
				controller.setZoom(18);
				controller.animateTo(myLoc.getMyLocation());
			}
		});
		map.getOverlays().add(myLoc);
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	@Override
    protected void onResume() {
     super.onResume();
     myLoc.enableMyLocation();//내위치찾는기능켜기
    }
	
	@Override
	protected void onPause() {
		super.onPause();
		myLoc.disableMyLocation();
	}
	
	class MyLocationOverlay2 extends MyLocationOverlay {
		public MyLocationOverlay2(Context context, MapView mapView) {
			super(context,mapView);
		}
	}
}