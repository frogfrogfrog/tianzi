package com.tianzi.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.tianzi.logic.CellData;

public class LevelData {
	private int level;
	// 横题目和纵题目
	private ArrayList<String> xtitle;
	private ArrayList<String> ytitle;
	// 题目的中文字和英文字
	private ArrayList<Character> ansCN;
	private ArrayList<Character> ansEN;
	// 10*10*3数组 [x][y][0]表示中文字，[1]表示横问题，[2]表示纵问题
	private int[][][] coordinate;
	// 保存用户的答题状态
	private CellData[][] userAns;

	// 结束标志位
	private int isEnd;

	private Context context;

	public LevelData(int level, Context context) throws IOException {
		this.context = context;
		this.level = level;
		// 横问题
		xtitle = new ArrayList<String>();
		// 纵问题
		ytitle = new ArrayList<String>();
		// 格子中的中文答案
		ansCN = new ArrayList<Character>();
		// 英文答案
		ansEN = new ArrayList<Character>();
		coordinate = new int[10][10][3];

		userAns = new CellData[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				userAns[i][j] = new CellData();
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				coordinate[i][j][0] = -1;
				coordinate[i][j][1] = -1;
				coordinate[i][j][2] = -1;
			}
		}
		this.initData();
	}

	private void addQuestion(int isXtitle, String title, String cn, String en,
			int xstart, int ystart) {
		char[] ansCNList = cn.toCharArray();
		char[] ansENList = en.toCharArray();
		if (isXtitle == 0) {
			// 横问题
			xtitle.add(title);
			int xTitleIndex = xtitle.size() - 1;
			for (int i = 0; i < ansCNList.length; i++) {
				ansCN.add(ansCNList[i]);
				int cnIndex = ansCN.size() - 1;
				ansEN.add(ansENList[i]);
				coordinate[xstart][ystart + i][0] = cnIndex;
				coordinate[xstart][ystart + i][1] = xTitleIndex;
			}
		} else {
			// 纵问题
			ytitle.add(title);
			int yTitleIndex = ytitle.size() - 1;
			for (int i = 0; i < ansCNList.length; i++) {
				ansCN.add(ansCNList[i]);
				int cnIndex = ansCN.size() - 1;
				ansEN.add(ansENList[i]);
				coordinate[xstart + i][ystart][0] = cnIndex;
				coordinate[xstart + i][ystart][2] = yTitleIndex;
			}
		}
	}

	private void initData() throws IOException {
		Log.v("yzx12", "进入initData");
		String path = String.valueOf(level) + ".puz";
		InputStream is = context.getResources().getAssets().open(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String temp = null;
		temp = br.readLine();
		while (temp != null) {
			// System.out.println(temp);
			String[] splitTemp = temp.split(" ");
			if (splitTemp.length >= 6) {
				this.addQuestion(Integer.parseInt(splitTemp[0]), splitTemp[1],
						splitTemp[2], splitTemp[3],
						Integer.parseInt(splitTemp[4]) - 1,
						Integer.parseInt(splitTemp[5]) - 1);
			}
			temp = br.readLine();
		}

		String ansPath = String.valueOf(level) + ".ans";

		try {
			FileInputStream fis = context.openFileInput(ansPath);

			// 如果level.ans已经创建，则说明用户已经玩过这个关卡，则导入用户数据
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader ansbr = new BufferedReader(isr);
			temp = ansbr.readLine();
			int count = 0;
			while (temp != null) {
				this.addAnswer(count, temp);
				temp = ansbr.readLine();
				count++;
			}
		} catch (FileNotFoundException e) {
			FileOutputStream fos = context.openFileOutput(ansPath,
					context.MODE_APPEND);

			PrintStream ps = new PrintStream(fos);

			for (int i = 0; i < 10; i++) {
				StringBuffer ansSb = new StringBuffer();
				for (int j = 0; j < 10; j++) {
					if (coordinate[i][j][0] >= 0) {
						// 这个格子有题目
						userAns[i][j].setState(1);
					} else {
						// 这个格子没有题目，不能在此处答题
						userAns[i][j].setState(0);
					}
					userAns[i][j].setxAxis(i);
					userAns[i][j].setyAxis(j);
					ansSb.append(String.valueOf(userAns[i][j].getState()));
					if (j != 9) {
						ansSb.append(" ");
					} else {
						ansSb.append("\r\n");
					}
				}
				ps.print(ansSb.toString());
			}
			ps.close();
		}

	}

	private void addAnswer(int row, String rowStr) {
		if (row <= 9) {
			String[] split = rowStr.split(" ");
			int count = 0;
			for (String temp : split) {
				char[] wordInfo = temp.toCharArray();
				userAns[row][count].setState(Integer.parseInt(String
						.valueOf(wordInfo[0])));
				if (wordInfo[0] == '2' || wordInfo[0] == '3') {
					// 说明此处有题目且玩家已经答题，英文或中文
					userAns[row][count].setWord(wordInfo[1]);
				}
				userAns[row][count].setxAxis(row);
				userAns[row][count].setyAxis(count);
				count++;
			}
		}
	}

	public void printGraph() {
		StringBuffer sb = new StringBuffer();
		sb.append("    ");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (coordinate[i][j][0] == -1) {
					System.out.print(sb.toString());
				} else {
					System.out.print(ansCN.get(coordinate[i][j][0]));
				}
				System.out.print(sb.toString());
			}
			System.out.println("");
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(userAns[i][j].getState());
				System.out.print(userAns[i][j].getWord() + " ");
			}
			System.out.println("");
		}
	}

	public void saveGameRecord() {
		if(this.getIsEnd()==1){
			//实例化SharedPreferences对象（第一步） 
			SharedPreferences sp= context.getSharedPreferences("achieve", 
			context.MODE_PRIVATE); 
			//实例化SharedPreferences.Editor对象（第二步） 
			SharedPreferences.Editor editor = sp.edit(); 
			//获取已经完成的关卡数目 
			int num=sp.getInt("achieveNum", 0);
			num++;
			editor.putInt("achieveNum", num);
			//提交当前数据 
			editor.commit(); 
		}
		
		String path = String.valueOf(level) + ".ans";

		try {
			FileOutputStream fos = context.openFileOutput(path,
					context.MODE_PRIVATE);
			PrintStream ps = new PrintStream(fos);
			ps.print("");
			ps.flush();
			ps.close();

			FileOutputStream fos1 = context.openFileOutput(path,
					context.MODE_APPEND);
			ps = new PrintStream(fos1);
			for (int i = 0; i < 10; i++) {
				StringBuffer ansSb = new StringBuffer();
				for (int j = 0; j < 10; j++) {
					if (userAns[i][j].getState() == 2
							|| userAns[i][j].getState() == 3) {
						ansSb.append(String.valueOf(userAns[i][j].getState())
								+ String.valueOf(userAns[i][j].getWord()));
					} else {
						ansSb.append(String.valueOf(userAns[i][j].getState()));
					}
					if (j != 9) {
						ansSb.append(" ");
					} else {
						ansSb.append("\r\n");
					}
				}
				ps.print(ansSb.toString());
			}
			ps.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<String> getXtitle() {
		return xtitle;
	}

	public void setXtitle(ArrayList<String> xtitle) {
		this.xtitle = xtitle;
	}

	public ArrayList<String> getYtitle() {
		return ytitle;
	}

	public void setYtitle(ArrayList<String> ytitle) {
		this.ytitle = ytitle;
	}

	public ArrayList<Character> getAnsCN() {
		return ansCN;
	}

	public void setAnsCN(ArrayList<Character> ansCN) {
		this.ansCN = ansCN;
	}

	public ArrayList<Character> getAnsEN() {
		return ansEN;
	}

	public void setAnsEN(ArrayList<Character> ansEN) {
		this.ansEN = ansEN;
	}

	public int[][][] getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(int[][][] coordinate) {
		this.coordinate = coordinate;
	}

	public CellData[][] getUserAns() {
		return userAns;
	}

	public void setUserAns(CellData[][] userAns) {
		this.userAns = userAns;
	}

	public int getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}

}
