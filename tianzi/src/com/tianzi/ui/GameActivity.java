package com.tianzi.ui;


import java.util.ArrayList;
import java.util.Map;

import com.example.tianzi.R;
import com.tianzi.logic.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;


public class GameActivity extends Activity {

	private int currentPosition=-1;
	Game_gridview_adapter adapter;
	Game_keyboard_adapter adapter2;
	GridView gridView;
	GridView gridView2;
	TextView questionView;
	String [] word = {
		"Q","W","E","R","T","Y","O","P",
		"A","S","D","F","G","H","J","K",
		"L","Z","X","C","V","B","N","M"
	};
	Logic logic;
	CellData[][] cellMap;
	ArrayList<CellData> cellList;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		logic = new LogicImpl();
		cellMap = logic.startGame(1);
		for(int i=0;i<10;i++){
			for(int n=0;n<10;n++){			
				cellList.add(cellMap[i][n]);
			}
		}
		
		questionView = (TextView) findViewById(R.id.game_textview_question);
		gridView = (GridView) findViewById(R.id.game_gridview);
		gridView2 =(GridView) findViewById(R.id.game_gridview2);
		adapter = new Game_gridview_adapter(GameActivity.this,cellList);
		
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				currentPosition = position;
				Map<String,String> question = logic.getTitles(position/10, position%10);
				questionView.setText("横向问题：" + question.get("xtitle") + "  纵向问题：" + question.get("ytitle"));
			}
			
		});
		
		adapter2 = new Game_keyboard_adapter(GameActivity.this);
		gridView2.setAdapter(adapter2);
		gridView2.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(currentPosition>=0){
//					data[currentPosition] = word[position];
				    adapter.notifyDataSetChanged();
				}
				
			}
			
		});
		
		
		
		
		
		
		
/*	TableLayout tableLayout = (TableLayout)findViewById(R.id.game_tablelayout);   
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
						data[0] = (String) v.getContext();
						adapter.notifyDataSetChanged();
					}
                	
                });
                tableRow.addView(tv);  
            }  
            tableLayout.addView(tableRow);  
        } 
*/ 
		
	}

	
}
