package com.example.tianzi.level;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.tianzi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

public class Test extends Activity{
	TextView data1;
	String result;
	Map<String, String> map= new HashMap<String, String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_test);
		data1=(TextView)findViewById(R.id.data1);

		
		Intent intent=getIntent();
		result=(String)intent.getStringExtra("city");
		data1.setText(result);
	}
}
