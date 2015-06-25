package com.example.tianzi;


import com.example.tianzi.level.ChoiceViewActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		Intent intent=new Intent(MainActivity.this,ChoiceViewActivity.class);
//    	startActivity(intent);
	}
}
