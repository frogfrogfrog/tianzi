package com.tianzi.logic;

import java.util.Map;

public interface Logic {
	/**
	 * 点击一个格子后，返回这个格子的横纵问题
	 * @return Map<String,String>  横问题的key“xtitle” 纵“ytitle” 如果不存在，则Map.get(String key)的结果为null
	 */
	public Map<String,String> getTitles(int x,int y);
	
	/**
	 * 用户选择关卡后，返回10*10的格子信息
	 * @param level 用户选择的关卡
	 * @return CellData[][] 10*10的格子信息
	 */
	public CellData[][] startGame(int level);
	
	/**
	 * 
	 * @param x 字的横坐标
	 * @param y	字的纵坐标		
	 * @param word 输入的大写字母
	 * @return ResultData
	 */
	public ResultData enterWord(int x,int y,char word);
	
	/**
	 * 保存游戏记录
	 */
	public void saveGameRecord();
	
}
