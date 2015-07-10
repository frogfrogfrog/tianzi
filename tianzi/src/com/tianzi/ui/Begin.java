package com.tianzi.ui;

import com.example.tianzi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;



public class Begin extends Activity {
    /** Called when the activity is first created. */
	
	Button logo;
	Button start;
	Button help;
	Button about;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ȡ������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ��ֹ��Ļ����
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		// ȫ��Ļ
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.begin);
        logo=(Button)findViewById(R.id.begin_logo);
        start=(Button)findViewById(R.id.begin_start);
        help=(Button)findViewById(R.id.begin_help);
//        about=(Button)findViewById(R.id.begin_about);
    }
    
}