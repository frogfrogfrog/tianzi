package com.example.tianzi;

import com.tianzi.logic.CellData;
import com.tianzi.logic.Logic;
import com.tianzi.logic.LogicImpl;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends Activity {

	Logic logic;
	CellData[][] cellMap;
	String test;
	TextView testView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		logic = new LogicImpl();
		cellMap = logic.startGame(1);
		for(int i=0;i<10;i++){
			for(int n=0;n<10;n++){			
				test+=cellMap[i][n];
			}
		}
		
		testView = (TextView) findViewById(R.id.test_activity_textview);
		testView.setText(test);
		
	}
}
