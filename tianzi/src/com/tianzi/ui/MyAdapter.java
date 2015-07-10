package com.tianzi.ui;


import com.example.tianzi.R;
import com.tianzi.logic.Logic;
import com.tianzi.logic.LogicImpl;

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
	
	
    private boolean result[]=new boolean [27];
//	boolean result[]={true,false,false,false,false,false,false,false,false,
//					  false,false,false,false,false,false,false,false,false,
//					  false,false,false,false,false,false,false,false,false};

    private Integer[][] imgshelp={
    		{R.drawable.level_button1,R.drawable.level_button_un1},
    		{R.drawable.level_button2,R.drawable.level_button_un2},
    		{R.drawable.level_button3,R.drawable.level_button_un3},
    		{R.drawable.level_button4,R.drawable.level_button_un4},
    		{R.drawable.level_button5,R.drawable.level_button_un5},
    		{R.drawable.level_button6,R.drawable.level_button_un6},
    		{R.drawable.level_button7,R.drawable.level_button_un7},
    		{R.drawable.level_button8,R.drawable.level_button_un8},
    		{R.drawable.level_button9,R.drawable.level_button_un9}
    		
    
    }; 
	 //图片id锟斤拷锟斤拷   
    private Integer[] imgs={   
            R.drawable.level_button1,  
            R.drawable.level_button2, 
            R.drawable.level_button3, 
            R.drawable.level_button4, 
            R.drawable.level_button5, 
            R.drawable.level_button6, 
            R.drawable.level_button7, 
            R.drawable.level_button8, 
            R.drawable.level_button9, 
           
//            R.drawable.level_button_un1, 
//            R.drawable.level_button_un2,   
//            R.drawable.level_button_un3,   
//            R.drawable.level_button_un4,   
//            R.drawable.level_button_un5,   
//            R.drawable.level_button_un6,   
//            R.drawable.level_button_un7,   
//            R.drawable.level_button_un8,                  
//            R.drawable.level_button_un9,

            
//            R.drawable.level_button1,   
//            R.drawable.level_button1,   
//            R.drawable.level_button1,   
//            R.drawable.level_button1,   
//            R.drawable.level_button1, 
//            R.drawable.level_button1,   
//            R.drawable.level_button1,
    };
    private Integer[] backImgs={
    		R.drawable.level_back1,
    		R.drawable.level_back2,
    		R.drawable.level_back3,
//    		R.drawable.levelgridback,
//    		R.drawable.levelgridback,
//    		R.drawable.levelgridback,
//    		R.drawable.testback1,
//    		R.drawable.testback1,
//    		R.drawable.testback1,
    		
    };
    //锟斤拷锟斤拷锟侥讹拷锟斤拷   
    Context context;   
    //锟斤拷锟届方锟斤拷   
    MyAdapter(Context context)   
    {   
        this.context=context;  
        Logic go=new LogicImpl();
        result=go.levelIsChieved();
    }   
    //锟斤拷锟斤拷锟斤拷锟�  
    public int getCount()   
    {   
        return backImgs.length;   
    }   
    //锟斤拷玫锟角把★拷锟�  
    public Object getItem(int item)   
    {   
        return item;   
    }   
    //锟斤拷玫锟角把★拷锟絠d   
    public long getItemId(int id)   
    {   
        return id;   
    }   
       
    //锟斤拷锟斤拷View锟斤拷锟斤拷   
    public View getView(int position,View convertView,ViewGroup parent)   
    {   
    	LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.level_linearlayout, null);
    	linearLayout.setBackgroundResource(backImgs[position]);
    	GridView gridView=(GridView)linearLayout.findViewById(R.id.gridview);
    	gridView.setNumColumns(3);
    	if(position==0){
    		for(int i=0;i<9;i++){
        		if(result[i]){
        			imgs[i]=imgshelp[i][0];
        		}
        		else imgs[i]=imgshelp[i][1];
        	}
    	gridView.setAdapter(new ImageAdapter());
    	gridView.setOnItemClickListener(new MyOnItemClickListener1()); 
    	}
    	else if(position==1){

    		for(int i=9;i<18;i++){
        		if(result[i]){
        			imgs[i-9]=imgshelp[i-9][0];
        		}
        		else imgs[i-9]=imgshelp[i-9][1];
        	}
    	gridView.setAdapter(new ImageAdapter());
    	gridView.setOnItemClickListener(new MyOnItemClickListener2()); 
    	}
    	else {
    		for(int i=18;i<27;i++){
        		if(result[i]){
        			imgs[i-18]=imgshelp[i-18][0];
        		}
        		else imgs[i-18]=imgshelp[i-18][1];
        	}
    	gridView.setAdapter(new ImageAdapter());
    	gridView.setOnItemClickListener(new MyOnItemClickListener3()); 
    	}    	      	   	
        return linearLayout;   
    }   
    class MyOnItemClickListener1 implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)  {
		if(result[arg2]){
			Bundle data=new Bundle();
			//data.putInt("level", arg2+1);
			data.putString("city", String.valueOf(arg2+1));
        	Intent intent=new Intent(context,Test.class);
        	intent.putExtras(data);
        	context.startActivity(intent);
		}
}}
    class MyOnItemClickListener2 implements AdapterView.OnItemClickListener {
 		@Override
 		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)  {
 		// arg0.getItemAtPosition(arg2);
 			if(result[arg2+9]){
 			Bundle data=new Bundle();
//         	data.putInt("level", arg2+10);
 			data.putString("city", String.valueOf(arg2+10));
         	Intent intent=new Intent(context,Test.class);
         	intent.putExtras(data);
         	context.startActivity(intent);
 			}
 }}
    class MyOnItemClickListener3 implements AdapterView.OnItemClickListener {
 		@Override
 		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)  {
 			if(result[arg2+18]){
 			Bundle data=new Bundle();
 			//data.putInt("level", arg2+19);
 			data.putString("city", String.valueOf(arg2+19));
         	Intent intent=new Intent(context,Test.class);
         	intent.putExtras(data);
         	context.startActivity(intent);
 			}
 }}

    
    class ImageAdapter extends BaseAdapter{
    	
    	 //锟斤拷锟斤拷锟斤拷锟�  
        public int getCount()   
        {   
            return imgs.length;   
        }   
        //锟斤拷玫锟角把★拷锟�  
        public Object getItem(int item)   
        {   
            return item;   
        }   
        //锟斤拷玫锟角把★拷锟絠d   
        public long getItemId(int id)   
        {   
            return id;   
        }   

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView=null;  
		
			
	        if(convertView==null)   
	        {   
	            //实锟斤拷锟斤拷ImageView锟斤拷锟斤拷   
	        	imageView=new ImageView(context);  
	            //锟斤拷锟斤拷ImageView锟斤拷锟襟布撅拷   
	        	imageView.setLayoutParams(new GridView.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.FILL_PARENT));
	            //锟斤拷锟矫边斤拷锟斤拷锟�  
	        	imageView.setAdjustViewBounds(false);   
	            //锟斤拷锟矫刻讹拷锟斤拷锟斤拷   
	        	imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            //锟斤拷锟矫硷拷锟�  
	        	imageView.setPadding(20,20,20,20); 
//	        	button.setText(""+position);
	        }else  
	        {   
	        	imageView=(ImageView)convertView;   
	        }   
	        //为ImageView锟斤拷锟斤拷图片锟斤拷源   
//	        button.setBackgroundResource(R.drawable.button); 
	        imageView.setImageResource(imgs[position]);
	        
//	        imageView.setOnClickListener(new View.OnClickListener() {
//		        public void onClick(View v) {
//		        		
//		        	        }
//		        	    });
			return imageView;
		}
    }
    
    
    
}   

