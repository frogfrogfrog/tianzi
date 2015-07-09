package com.tianzi.logic;

/**
 * 格子中的数据
 * @author yzx12
 *
 */
public class CellData {
	//格子状态，0表示此处没有题目，不能输入，1表示有题目，但是用户未填写答案，2表示填写了英文答案，3表示题目回答正确，显示中文
	private int state;
	private char word;
	//坐标
	private int xAxis;
	private int yAxis;
	public CellData(int state,char word){
		this.state=state;
		this.word=word;
	}
	public CellData(){
		
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public char getWord() {
		return word;
	}
	public void setWord(char word) {
		this.word = word;
	}
	public int getxAxis() {
		return xAxis;
	}
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	public int getyAxis() {
		return yAxis;
	}
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
}
