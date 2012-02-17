package com.byeonggon.memap;

import static android.provider.BaseColumns._ID;
import static com.byeonggon.memap.Constants.TABLE_NAME;
import static com.byeonggon.memap.Constants.SUBJECT;
import static com.byeonggon.memap.Constants.CONTENT;
import static com.byeonggon.memap.Constants.TIME;
import static com.byeonggon.memap.Constants.GPSX;
import static com.byeonggon.memap.Constants.GPSY;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemoData extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "memo.db";
	private static final int DATABASE_VERSION = 1;
	
	/**Create a helper object for the Events database */
	public MemoData(Context ctx){
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + SUBJECT
				+ " INTEGER," + CONTENT + " INTEGER," + TIME
				+ " INTEGER," + GPSX + " INTEGER," + GPSY
				+ " TEXT NOT NULL);");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion,
			int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		onCreate(db);
	}
}
