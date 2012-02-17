package com.byeonggon.memap;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns{
	public static final String TABLE_NAME = "memo_list";
	
	//memo_list 데이터베이스의 행들
	public static final String SUBJECT = "subject";
	public static final String CONTENT = "content";
	public static final String TIME = "time";
	public static final String GPSX = "gpsx";
	public static final String GPSY = "gpsy";
}
