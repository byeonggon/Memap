package com.byeonggon.memap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Memo_write extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.memo_write);
		Button terminate = (Button)findViewById(R.id.memo_save);
		terminate.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				finish();
			}
		});
	}
}
