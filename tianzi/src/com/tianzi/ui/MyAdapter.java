package com.tianzi.ui;


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
	 //ͼƬid����   
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
    //�����Ķ���   
    Context context;   
    //���췽��   
    MyAdapter(Context context)   
    {   
        this.context=context;   
    }   
    //�������   
    public int getCount()   
    {   
        return backImgs.length;   
    }   
    //��õ�ǰѡ��   
    public Object getItem(int item)   
    {   
        return item;   
    }   
    //��õ�ǰѡ��id   
    public long getItemId(int id)   
    {   
        return id;   
    }   
       
    //����View����   
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
    	
    	 //�������   
        public int getCount()   
        {   
            return imgs.length;   
        }   
        //��õ�ǰѡ��   
        public Object getItem(int item)   
        {   
            return item;   
        }   
        //��õ�ǰѡ��id   
        public long getItemId(int id)   
        {   
            return id;   
        }   

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView=null;   
	        if(convertView==null)   
	        {   
	            //ʵ����ImageView����   
	        	imageView=new ImageView(context);  
	            //����ImageView���󲼾�   
	        	imageView.setLayoutParams(new GridView.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.FILL_PARENT));
	            //���ñ߽����   
	        	imageView.setAdjustViewBounds(false);   
	            //���ÿ̶�����   
	        	imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            //���ü��   
	        	imageView.setPadding(30,30,30,30); 
//	        	button.setText(""+position);
	        }else  
	        {   
	        	imageView=(ImageView)convertView;   
	        }   
	        //ΪImageView����ͼƬ��Դ   
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

