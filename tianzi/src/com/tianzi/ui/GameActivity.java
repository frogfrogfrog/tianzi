package com.tianzi.ui;


import com.example.tianzi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameActivity extends Activity {

	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;  
	private final int FP = ViewGroup.LayoutParams.FILL_PARENT;
	
	Game_gridview_adapter adapter;
	GridView gridView;
	TableRow tableRow;
	String [] word = {
		"Q","W","E","R","T","Y","O","P",
		"A","S","D","F","G","H","J","K",
		"L","Z","X","C","V","B","N","M"
	};
	String[] data = new String[100];
	String input;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		gridView = (GridView) findViewById(R.id.game_gridview);
		
		//
		//测试用
		//
		for(int i=0;i<100;i++){
			data[i] = String.valueOf(i);
		}
		
		adapter = new Game_gridview_adapter(GameActivity.this,data);
		gridView.setAdapter(adapter);
		
		
		
		TableLayout tableLayout = (TableLayout)findViewById(R.id.game_tablelayout);   
        for(int row=0;row<3;row++)  
        {  
            TableRow tableRow=new TableRow(this);  
            for(int col=0;col<8;col++)  
            {  
            	input = word[row*8+col];
                TextView tv=new TextView(this);  
                tv.setText(input);
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.game_button3);
                tv.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
//						data[0] = ;
						adapter.notifyDataSetChanged();
					}
                	
                });
                tableRow.addView(tv);  
            }  
            tableLayout.addView(tableRow);  
        }  
		
	}

	
}
