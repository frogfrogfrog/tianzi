package com.tianzi.logic;

import java.util.ArrayList;

/**
 * 用户输入一个大写字母后，返回的数据
 * @author yzx12
 *
 */
public class ResultData {
	//游戏是否结束
	private int isEnd;
	//如果用户输入一个字，解出了某个题目，则返回该题目的所有格子信息。UI根据该信息进行翻转，显示中文字。
	private ArrayList<CellData> cellData;
	
	public ResultData(){
		
	}

	public int getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}

	public ArrayList<CellData> getCellData() {
		return cellData;
	}

	public void setCellData(ArrayList<CellData> cellData) {
		this.cellData = cellData;
	}
}
