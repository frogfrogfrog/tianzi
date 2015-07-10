package com.tianzi.ui;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tianzi.R;

public class Help extends Activity{
	TextView data1;
	String result;
	Map<String, String> map= new HashMap<String, String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		
	}
}
