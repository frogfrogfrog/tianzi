package com.tianzi.ui;

import java.util.ArrayList;
import java.util.Map;

import com.tianzi.logic.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class GameActivity extends Activity {

	private int currentPosition = -1;
	Game_gridview_adapter adapter;
	Game_keyboard_adapter adapter2;
	GridView gridView;
	GridView gridView2;
	TextView questionView;
	TextView levelView;
	TextView lastView;
	Button backButton;
	char[] word = { 'Q', 'W', 'E', 'R', 'T', 'Y', 'O', 'P', 'A', 'S', 'D', 'F',
			'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M' };
	Logic logic;
	CellData[][] cellMap;
	ArrayList<CellData> cellList = new ArrayList<CellData>();
	ResultData resultData;
	int level = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_game);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String levelStr = bundle.getString("level");
		level = Integer.parseInt(levelStr);

		levelView = (TextView) findViewById(R.id.level_name_textview);
		levelView.setText("第" + String.valueOf(level) + "关");

		backButton = (Button) findViewById(R.id.game_back_button);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GameActivity.this,
						ChoiceViewActivity.class);
				startActivity(intent);
			}
		});

		logic = new LogicImpl();
		cellMap = logic.startGame(level, this);
		for (int i = 0; i < 10; i++) {
			for (int n = 0; n < 10; n++) {
				cellList.add(cellMap[i][n]);
			}
		}

		questionView = (TextView) findViewById(R.id.game_textview_question);
		gridView = (GridView) findViewById(R.id.game_gridview);
		gridView2 = (GridView) findViewById(R.id.game_gridview2);

		adapter = new Game_gridview_adapter(GameActivity.this, cellList);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				adapter.setSelectedItem(position);
				adapter.notifyDataSetChanged();
				currentPosition = position;
				Map<String, String> question = logic.getTitles(position / 10,
						position % 10);
				String temp = "";
				if (question.get("xtitle") != null) {
					temp = "横问题：" + question.get("xtitle") + "  ";
				}
				if (question.get("ytitle") != null) {
					temp = temp + "纵问题：" + question.get("ytitle");
				}
				questionView.setText(temp);
			}

		});

//		gridView.setSelector(R.drawable.game_button4);
		
		adapter2 = new Game_keyboard_adapter(GameActivity.this);
		gridView2.setAdapter(adapter2);
		gridView2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (currentPosition >= 0) {
					CellData temp = cellList.get(currentPosition);
					if (temp.getState() == 2 || temp.getState() == 1) {

						cellList.get(currentPosition).setWord(word[position]);
						cellList.get(currentPosition).setState(2);
						resultData = logic.enterWord(currentPosition / 10,
								currentPosition % 10, word[position]);
						if (resultData.getIsCorrect()) {
							ArrayList<CellData> ansList = resultData
									.getCellData();
							for (CellData ans : ansList) {
								cellList.get(
										ans.getxAxis() * 10 + ans.getyAxis())
										.setWord(ans.getWord());
								cellList.get(
										ans.getxAxis() * 10 + ans.getyAxis())
										.setState(ans.getState());
							}
						}
						
						int next=resultData.getNextX()*10+resultData.getNextY();
						adapter.setSelectedItem(next);
						adapter.notifyDataSetChanged();
						currentPosition = next;
						Map<String, String> question = logic.getTitles(next / 10,
								next % 10);
						String temp1 = "";
						if (question.get("xtitle") != null) {
							temp1 = "横问题：" + question.get("xtitle") + "  ";
						}
						if (question.get("ytitle") != null) {
							temp1 = temp1 + "纵问题：" + question.get("ytitle");
						}
						questionView.setText(temp1);
						
						
						if (resultData.getIsEnd()) {
							Dialog alertDialog = new AlertDialog.Builder(view
									.getContext())
									.setMessage("恭喜您完成本关卡！")
									.setIcon(R.drawable.ic_launcher)
									.setNegativeButton(
											"返回",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													Intent intent = new Intent(
															GameActivity.this,
															ChoiceViewActivity.class);
													startActivity(intent);
												}
											})
									.setPositiveButton(
											"进入下一关",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													Bundle data = new Bundle();
													// data.putInt("level",
													// arg2+19);
													data.putString(
															"level",
															String.valueOf(++level));
													Intent intent = new Intent(
															GameActivity.this,
															GameActivity.class);
													intent.putExtras(data);
													startActivity(intent);
												}
											}).create();
							alertDialog.show();
						}
					}
				}

			}

		});

		/*
		 * TableLayout tableLayout =
		 * (TableLayout)findViewById(R.id.game_tablelayout); for(int
		 * row=0;row<3;row++) { TableRow tableRow=new TableRow(this); for(int
		 * col=0;col<8;col++) { input = word[row*8+col]; TextView tv=new
		 * TextView(this); tv.setText(input); tv.setGravity(Gravity.CENTER);
		 * tv.setBackgroundResource(R.drawable.game_button3);
		 * tv.setOnClickListener(new OnClickListener(){
		 * 
		 * @Override public void onClick(View v) { data[0] = (String)
		 * v.getContext(); adapter.notifyDataSetChanged(); }
		 * 
		 * }); tableRow.addView(tv); } tableLayout.addView(tableRow); }
		 */

	}
	
	private void setTitleOfCell(int position){
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Intent intent = new Intent(
					GameActivity.this,
					ChoiceViewActivity.class);
			startActivity(intent);
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}
