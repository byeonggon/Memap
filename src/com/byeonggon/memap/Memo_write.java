package com.byeonggon.memap;

import static com.byeonggon.memap.Constants.TABLE_NAME;
import static com.byeonggon.memap.Constants.SUBJECT;
import static com.byeonggon.memap.Constants.CONTENT;
import static com.byeonggon.memap.Constants.TIME;
import static com.byeonggon.memap.Constants.GPSX;
import static com.byeonggon.memap.Constants.GPSY;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class Memo_write extends Activity implements LocationListener{
	private MemoData memos;
	private LocationManager manager;
	private Location loc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		memos = new MemoData(this);
		
		manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0, this);
		manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 0, this);
		
		setContentView(R.layout.memo_write);
		Button save = (Button)findViewById(R.id.memo_save);
		save.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				addMemo();
			}
		});
	}
	
	private void addMemo() {
		SQLiteDatabase db = memos.getWritableDatabase();
		ContentValues values = new ContentValues();
		EditText subject, content;
		
		subject = (EditText)findViewById(R.id.memo_subject);
		content = (EditText)findViewById(R.id.memo_content);
		values.put(SUBJECT, subject.getText().toString());
		values.put(CONTENT, content.getText().toString());
		values.put(TIME, System.currentTimeMillis());
		if(loc!=null){
			values.put(GPSX, Double.toString(loc.getLatitude()));
			values.put(GPSY, Double.toString(loc.getLongitude()));
		}
		else{
			values.put(GPSX, "null");
			values.put(GPSY, "null");
		}
		db.insertOrThrow(TABLE_NAME, null, values);
		finish();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		manager.removeUpdates(this);
		
		super.onDestroy();
	}

	
	public void onLocationChanged(Location loc_now) {
		loc=loc_now;
	}
	
	public void onStatusChanged(String provider, int status, Bundle extras) {}

	public void onProviderEnabled(String provider) {}

	public void onProviderDisabled(String provider) {}
}
