package com.tianzi.ui;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;


public class ChoiceViewActivity extends Activity {
	 /** Called when the activity is first created. */
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
        setContentView(R.layout.level_main);
        Gallery g=(Gallery)findViewById(R.id.gallery);
        g.setAdapter(new MyAdapter(ChoiceViewActivity.this));//����myAdapter 
        registerForContextMenu(g);
        
   //     g.setOnItemClickListener(new MyOnItemClickListener());
        
        Button button=(Button)findViewById(R.id.level_button);
    	button.setBackgroundResource(R.drawable.buttonseloctor);
    	button.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent intent=new Intent(ChoiceViewActivity.this,MainActivity.class);
	        	startActivity(intent);
	        	        }
	        	    });
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
       Toast.makeText(this, "Longpress: " + info.position, Toast.LENGTH_SHORT).show();        
        return true;
    }
    
//    class MyOnItemClickListener implements AdapterView.OnItemClickListener {
//		@Override
//		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)  {
//		// arg0.getItemAtPosition(arg2);
//			Bundle data=new Bundle();
//        	data.putString("city", String.valueOf(arg2));
//        	Intent intent=new Intent(ChoiceViewActivity.this,Test.class);
//        	intent.putExtras(data);
//        	startActivity(intent);
//}}
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Intent intent = new Intent(
					ChoiceViewActivity.this,
					MainActivity.class);
			startActivity(intent);
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}