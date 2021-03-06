package com.tianzi.ui;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class Game_keyboard_adapter extends BaseAdapter{
	private String [] word = {
			"Q","W","E","R","T","Y","O","P",
			"A","S","D","F","G","H","J","K",
			"L","Z","X","C","V","B","N","M"
		};
	private Context context;
	private LayoutInflater inflater = null;
	
	Game_keyboard_adapter(Context context){
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return word.length;
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
		holder.tv.setText(word[position]);
		holder.tv.setBackgroundResource(R.drawable.game_button3);
		
		return convertView;
	}

}
