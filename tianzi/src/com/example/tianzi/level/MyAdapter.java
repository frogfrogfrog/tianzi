package com.example.tianzi.level;


import com.example.tianzi.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter{
	 //图片id数组   
    private Integer[] imgs={   
            R.drawable.level_button,   
            R.drawable.level_button,   
            R.drawable.level_button,   
            R.drawable.level_button,   
            R.drawable.level_button,   
            R.drawable.level_button,   
            R.drawable.level_button,   
            R.drawable.level_button,                  
            R.drawable.level_button,   
            R.drawable.level_button,   
            R.drawable.level_button,   
            R.drawable.level_button,   
            R.drawable.level_button,   
            R.drawable.level_button, 
            R.drawable.level_button,   
            R.drawable.level_button,
    };
    private Integer[] backImgs={
    		R.drawable.level_back1,
    		R.drawable.level_back2,
    		R.drawable.level_back3,
    		
    };
    //上下文对象   
    Context context;   
    //构造方法   
    MyAdapter(Context context)   
    {   
        this.context=context;   
    }   
    //获得数量   
    public int getCount()   
    {   
        return backImgs.length;   
    }   
    //获得当前选项   
    public Object getItem(int item)   
    {   
        return item;   
    }   
    //获得当前选项id   
    public long getItemId(int id)   
    {   
        return id;   
    }   
       
    //创建View方法   
    public View getView(int position,View convertView,ViewGroup parent)   
    {   
    	LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.level_linearlayout, null);
    	linearLayout.setBackgroundResource(backImgs[position]);
    	GridView gridView=(GridView)linearLayout.findViewById(R.id.gridview);
    	gridView.setNumColumns(4);
    	gridView.setAdapter(new ImageAdapter());
    	gridView.setOnItemClickListener(new MyOnItemClickListener()); 
    	
    	
    	
    	
        return linearLayout;   
    }   
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)  {
		// arg0.getItemAtPosition(arg2);
			Bundle data=new Bundle();
        	data.putString("city", String.valueOf(arg2));
        	Intent intent=new Intent(context,Test.class);
        	intent.putExtras(data);
        	context.startActivity(intent);
}}

    
    class ImageAdapter extends BaseAdapter{
    	
    	 //获得数量   
        public int getCount()   
        {   
            return imgs.length;   
        }   
        //获得当前选项   
        public Object getItem(int item)   
        {   
            return item;   
        }   
        //获得当前选项id   
        public long getItemId(int id)   
        {   
            return id;   
        }   

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView=null;   
	        if(convertView==null)   
	        {   
	            //实例化ImageView对象   
	        	imageView=new ImageView(context);  
	            //设置ImageView对象布局   
	        	imageView.setLayoutParams(new GridView.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.FILL_PARENT));
	            //设置边界对齐   
	        	imageView.setAdjustViewBounds(false);   
	            //设置刻度类型   
	        	imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            //设置间距   
	        	imageView.setPadding(30,30,30,30); 
//	        	button.setText(""+position);
	        }else  
	        {   
	        	imageView=(ImageView)convertView;   
	        }   
	        //为ImageView设置图片资源   
//	        button.setBackgroundResource(R.drawable.button);   
	        imageView.setImageResource(R.drawable.level_button);
//	        imageView.setOnClickListener(new View.OnClickListener() {
//		        public void onClick(View v) {
//		        		
//		        	        }
//		        	    });
			return imageView;
		}
    }
    
    
    
}   

