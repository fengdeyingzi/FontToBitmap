package com.xl.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.xl.game.tool.SkyFontTool;
import com.xl.util.ImageUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class DrawSkyFontView extends View{
	
	
	//字体目录
	String font_path = "D:\\workspace\\FontToBitmap"+File.separatorChar+"gb16_mrpoid.uc2";
	
	//导出目录
	String out_path = "D:\\Download\\";
	
	//导出的map
	ArrayList<FontClass> list_fontClass;

	public DrawSkyFontView(Context context) {
		super(context);
		try {
			initView();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	private void initView() throws FileNotFoundException{
		invalidate();
		SkyFontTool fontTool = new SkyFontTool(new RandomAccessFile(font_path, "r"));
		list_fontClass = new ArrayList<>();
		list_fontClass.add(new FontClass("英文字符符号", 0x20, 0x7e));
		list_fontClass.add(new FontClass("象形文字 扩展A",0x3400 , 0x4DB5));
		list_fontClass.add(new FontClass("象形文字",0x4e00,0x9FD5));
		list_fontClass.add(new FontClass("中日韩兼容形式",0xFE30,0xFE4F));
		list_fontClass.add(new FontClass("小写变体形式",0xFE50,0xFE6B));
		list_fontClass.add(new FontClass("半角和全角",0xFF00,0xFFEF));
		list_fontClass.add(new FontClass("中日韩符号和标点",0x3000,0x303F));
		
		
		
		 
	        boolean isStart = true;
	        for (int list_i=0;list_i<list_fontClass.size();list_i++) {
	            FontClass fontClass = list_fontClass.get(list_i);
	            String key = fontClass.dirname;
	            File file_dir = new File(out_path+key);
	            file_dir.mkdirs();
	            StringBuffer buffer = new StringBuffer();
	            buffer.append(key+": ");
	            String temp = String.format("%x", fontClass.start_index).toUpperCase();
	            String temp2 = String.format("%x", fontClass.end_index).toUpperCase();
	            buffer.append("$"+temp+" - "+temp2);
	            for(int i=fontClass.start_index;i<=fontClass.end_index;i++){
	            	String out_name = String.format("%x", i);
	            	
	            	out_name = out_name.toUpperCase();
	            	
	            	outChar(fontTool, out_path+key+File.separator+out_name+".png", (char)i);
	            }
	            System.out.println(buffer.toString());
	            

	        }
	       System.out.println("输出完成");
	       fontTool.xl_font_sky16_close();
	}
	
	
	//导出一个文字 参数：fontTool 文件路径 字号
	public int outChar(SkyFontTool fontTool, String outPath, char c){
		Bitmap bitmap = Bitmap.createBitmap(16, 16, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		fontTool.setCanvas(canvas);
		fontTool.xl_font_sky16_drawChar((char)c,0,0,0xff000000);
		FileOutputStream out;
		
//			out = new FileOutputStream(outPath);
		if(new File(outPath).isFile()){
			return -1;
		}
			ImageUtil.zoomBitmap(bitmap, 160, 160, outPath);
//			if(bitmap.compress(Bitmap.CompressFormat.PNG,80,out))
//			return 0;
	
		
		
		return -1;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		/*
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(getContext().getResources().getAssets().open("icon.png"));
			canvas.drawBitmap(bitmap, 20, 20, null);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		*/
		SkyFontTool fontTool;
		try {
			fontTool = new SkyFontTool(new RandomAccessFile(font_path, "r"));
			fontTool.setCanvas(canvas);
			int n = 0;
			for(int i=0x20;i<=0x7e;i++){
				fontTool.xl_font_sky16_drawChar((char)i,0+n,0,0xff000000);
				n+=16;
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		super.onDraw(canvas);
		
	}
	
	class FontClass{
		String dirname;
		int start_index;
		int end_index;
		public FontClass(String name,int start,int end){
			this.dirname = name;
			this.start_index = start;
			this.end_index = end;
		}
		
		public int getStart(){
			return this.start_index;
		}
		
		public int getEnd(){
			return this.end_index;
		}
		
	}

}
