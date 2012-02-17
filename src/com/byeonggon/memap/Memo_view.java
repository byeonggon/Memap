package com.byeonggon.memap;

import static android.provider.BaseColumns._ID;
import static com.byeonggon.memap.Constants.TABLE_NAME;
import static com.byeonggon.memap.Constants.SUBJECT;
import static com.byeonggon.memap.Constants.CONTENT;
import static com.byeonggon.memap.Constants.TIME;
import static com.byeonggon.memap.Constants.GPSX;
import static com.byeonggon.memap.Constants.GPSY;

import android.app.ListActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;

public class Memo_view extends ListActivity {
	private MemoData memos;
	private static String[] FROM = {SUBJECT, CONTENT, _ID, TIME, GPSX, GPSY};
	private static String ORDER_BY = _ID + " DESC";
	private static int[] TO = {R.id.subject, R.id.content};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_view);
		
		memos = new MemoData(this);
		
		try{
			Cursor cursor = getMemo();
			showMemo(cursor);
		} finally {
			memos.close();
		}
	}
	
	private Cursor getMemo(){
		SQLiteDatabase db = memos.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);
		startManagingCursor(cursor);
		return cursor;
	}
	
	private void showMemo(Cursor cursor){
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.list, cursor, FROM, TO);
		setListAdapter(adapter);
	}
}