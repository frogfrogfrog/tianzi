package com.tianzi.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.tianzi.data.LevelData;

public class LogicImpl implements Logic{
	private LevelData ld;
	@Override
	public Map<String, String> getTitles(int x,int y) {
		// TODO Auto-generated method stub
		Map<String,String> result=new HashMap<String,String>();
		int[][][] coordinate=ld.getCoordinate();
		ArrayList<String> xtitles=ld.getXtitle();
		ArrayList<String> ytitles=ld.getYtitle();
		if(coordinate[x][y][1]!=-1){
			result.put("xtitle", xtitles.get(coordinate[x][y][1]));
		}
		if(coordinate[x][y][2]!=-1){
			result.put("ytitle", ytitles.get(coordinate[x][y][2]));
		}
		return result;
	}
	@Override
	public CellData[][] startGame(int level) {
		// TODO Auto-generated method stub
		try {
			ld=new LevelData(level);
			CellData[][] result=ld.getUserAns();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	@Override
	public ResultData enterWord(int x, int y, char word) {
		// TODO Auto-generated method stub
		CellData[][] cdList=ld.getUserAns();
		cdList[x][y].setState(2);
		cdList[x][y].setWord(word);
		int[][][] coordinate=ld.getCoordinate();
		
		if(cdList[x][y].getState()==0){
			//该位置没有题目
			return null;
		}	
		ResultData result=new ResultData();
		ArrayList<CellData> resultCD=new ArrayList<CellData>();
		if(coordinate[x][y][1]>=0){
			//填的这个字处于一个横问题中
			ArrayList<CellData> temp=new ArrayList<CellData>();
			int flag=1;
			for(int i=0;i<10-y;i++){
				if(cdList[x][y+i].getState()==2){
					//说明 第[x][y+i]也填了英文答案
					temp.add(cdList[x][y+i]);
				}else if(cdList[x][y+i].getState()==1){
					//出现了该题目有格子未填答案的现象
					flag=0;
					break;
				}else if(cdList[x][y+i].getState()==0){
					//到了题目的边界
					break;
				}
			}
			if(flag==1){
				//说明xy右边的格子都已经填了英文字母答案，现在遍历左边的格子
				for(int i=1;i<y+1;i++){
					if(cdList[x][y-i].getState()==2){
						//说明 第[x][y-i]也填了英文答案
						temp.add(cdList[x][y-i]);
					}
					else if(cdList[x][y-i].getState()==1){
						//出现了该题目有格子未填答案的现象
						flag=0;
						break;
					}else if(cdList[x][y-i].getState()==0){
						//到了题目的边界
						break;
					}
				}
			}
			if(flag==1){
				//该题目的所以格子都填了英文答案。
				judgeAnsIsCorrect(resultCD, temp);
			}
		}
		if(coordinate[x][y][2]>=0){
			//填的这个字处于一个纵问题中
			ArrayList<CellData> temp=new ArrayList<CellData>();
			int flag=1;
			for(int i=0;i<10-y;i++){
				if(cdList[x+i][y].getState()==2){
					//说明 第[x+i][y]也填了英文答案
					temp.add(cdList[x+i][y]);
				}else if(cdList[x+i][y].getState()==1){
					//出现了该题目有格子未填答案的现象
					flag=0;
					break;
				}else if(cdList[x+i][y].getState()==0){
					//到了题目的边界
					break;
				}
			}
			if(flag==1){
				//说明xy下边的格子都已经填了英文字母答案，现在遍历上边的格子
				for(int i=1;i<y+1;i++){
					if(cdList[x-i][y].getState()==2){
						//说明 第[x-i][y]也填了英文答案
						temp.add(cdList[x-i][y]);
					}
					else if(cdList[x-i][y].getState()==1){
						//出现了该题目有格子未填答案的现象
						flag=0;
						break;
					}else if(cdList[x-i][y].getState()==0){
						//到了题目的边界
						break;
					}
				}
			}
			if(flag==1){
				//该题目的所以格子都填了英文答案。
				judgeAnsIsCorrect(resultCD, temp);
			}
		}
		if(resultCD.size()==0){
			//说明要么答案没填完，要么不对
			int nextX;
			int nextY;
			if(coordinate[x][y][1]>=0 && cdList[x][y+1].getState()==1){
				nextX=x;
				nextY=y+1;
			}
			if(coordinate[x][y][2]>=0 && cdList[x+1][y].getState()==1){
				nextX=x+1;
				nextY=y;
			}
		}
		result.setCellData(resultCD);
		return result;
	}
	@Override
	public void saveGameRecord() {
		// TODO Auto-generated method stub
		ld.saveGameRecord();
	}
	
	private void judgeAnsIsCorrect(ArrayList<CellData> resultCD,ArrayList<CellData> temp){
		int[][][] coordinate=ld.getCoordinate();
		ArrayList<Character> ansEN=ld.getAnsEN();
		ArrayList<Character> ansCN=ld.getAnsCN();
		for(int i=0;i<temp.size();i++){
			int xAxis=temp.get(i).getxAxis();
			int yAxis=temp.get(i).getyAxis();
			if(temp.get(i).getWord()==ansEN.get(coordinate[xAxis][yAxis][0])){
				//这个格子答案正确
			}else{
				//这个格子答案不正确，情况暂存区temp
				temp.clear();
				break;
			}
		}
		if(temp.size()>0){
			//说明整个题目正确
			for(int i=0;i<temp.size();i++){
				int xAxis=temp.get(i).getxAxis();
				int yAxis=temp.get(i).getyAxis();
				temp.get(i).setState(3);
				temp.get(i).setWord(ansCN.get(coordinate[xAxis][yAxis][0]));
			}
			resultCD.addAll(temp);
		}
	}
	@Override
	public boolean[] levelIsChieved() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
