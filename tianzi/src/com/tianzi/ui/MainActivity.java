package com.tianzi.ui;



import com.example.tianzi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

//	@Override 
//	//xmr12 2015.6.25
//	//zjw12
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
////		Intent intent=new Intent(MainActivity.this,ChoiceViewActivity.class);
////    	startActivity(intent);
//		Intent intent=new Intent(MainActivity.this,GameActivity.class);
//    	startActivity(intent);
//	}
	Button start;
	Button help;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 取消标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 禁止屏幕休眠
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		// 全屏幕
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.begin);
        start=(Button)findViewById(R.id.begin_start);
        start.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent intent=new Intent(MainActivity.this,ChoiceViewActivity.class);
	        	startActivity(intent);
	        	        }
	        	    });
        help=(Button)findViewById(R.id.begin_help);
        help.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent intent=new Intent(MainActivity.this,Help.class);
	        	startActivity(intent);
	        	        }
	        	    });
    }
}
