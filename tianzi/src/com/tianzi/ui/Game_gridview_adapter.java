package com.tianzi.ui;

import java.util.ArrayList;

import com.tianzi.logic.CellData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Game_gridview_adapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater = null;
	private ArrayList<CellData> cellList;
	
	private int selectedItem=-1;
	
	
	public int getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(int selectedItem) {
		this.selectedItem = selectedItem;
	}
	Game_gridview_adapter(Context context,ArrayList<CellData> cellList){
		this.context=context;
		this.cellList = cellList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cellList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	private class Holder{
		private TextView tv;

		public TextView getTv() {
			return tv;
		}

		public void setTv(TextView tv) {
			this.tv = tv;
		}

	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder;
		inflater = LayoutInflater.from(context);
		if(convertView == null){
			convertView = inflater.inflate(R.layout.game_gridview_item, null);
			holder = new Holder();
			holder.tv = (TextView) convertView.findViewById(R.id.game_gridview_item_textview);
			convertView.setTag(holder);	
		}else{
			holder = (Holder) convertView.getTag();
		}
		if(cellList.get(position).getState()==0){//不能输入的格子
			
			holder.tv.setBackgroundResource(R.drawable.game_button1);
		}else if(cellList.get(position).getState()==1){//未填写的格子
			if(position==selectedItem){
				holder.tv.setBackgroundResource(R.drawable.game_button4);
			}else{
				holder.tv.setBackgroundResource(R.drawable.game_button3);
			}
		}else if(cellList.get(position).getState()==2){//填写英文的格子
			holder.tv.setText(cellList.get(position).getWord()+"");
			if(position==selectedItem){
				holder.tv.setBackgroundResource(R.drawable.game_button4);
			}else{
				holder.tv.setBackgroundResource(R.drawable.game_button3);
			}
		}else if(cellList.get(position).getState()==3){//填写中文的格子
			holder.tv.setText(cellList.get(position).getWord()+"");
			if(position==selectedItem){
				holder.tv.setBackgroundResource(R.drawable.game_button4);
			}else{
				holder.tv.setBackgroundResource(R.drawable.game_button2);
			}
		}
		
		return convertView;
	}

}
