package com.tianzi.logic;

import java.util.ArrayList;

/**
 * 用户输入一个大写字母后，返回的数据
 * @author yzx12
 *
 */
public class ResultData {
	//游戏是否结束
	private boolean isEnd;
	
	//答题是否使某一个题目被成功解答，如果是，要取出CellData中的数据进行更新
	private boolean isCorrect;

	//如果用户输入一个字，解出了某个题目，则返回该题目的所有格子信息。UI根据该信息进行翻转，显示中文字。
	private ArrayList<CellData> cellData;
	
	//如果该问题还有要输入的格子，返回下个格子的位置
	private int nextX;
	private int nextY;
	
	
	
	
	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public int getNextX() {
		return nextX;
	}

	public void setNextX(int nextX) {
		this.nextX = nextX;
	}

	public int getNextY() {
		return nextY;
	}

	public void setNextY(int nextY) {
		this.nextY = nextY;
	}

	public ResultData(){
		
	}

	public boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public ArrayList<CellData> getCellData() {
		return cellData;
	}

	public void setCellData(ArrayList<CellData> cellData) {
		this.cellData = cellData;
	}
	
	
	
}
