package com.tianzi.ui;



import com.example.tianzi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override 
	//xmr12 2015.6.25
	//zjw12
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		Intent intent=new Intent(MainActivity.this,ChoiceViewActivity.class);
//    	startActivity(intent);
		Intent intent=new Intent(MainActivity.this,GameActivity.class);
    	startActivity(intent);
	}
}
