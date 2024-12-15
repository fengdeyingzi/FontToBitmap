package com.xl.game.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.xl.util.ImageUtil;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/*
 * 点阵字体制作辅助工具
 * 生成脚本精灵脚本
 * 生成字库导入序列
 * 生成字库信息 记录成txt
 * 文字可以重复
 */
public class FontFactory {
	String text;
	//字体目录
	static String font_path = "gb16_mrpoid.uc2";
	
	public FontFactory(String text){
		this.text = dereplication_old(text);
	}
	
	
	//获取不重复的字符串(效率低，已换成新方法)
		private String dereplication_old(String str)
		{
			List<String> data = new ArrayList<String>();
			StringBuffer buf=new StringBuffer();
			for(int i = 0; i<str.length(); i++)
			{
				String s = str.substring(i, i+1);
				if(!data.contains(s))
					data.add(s);
			}
			//String result = "";
			for(String s : data)
				buf.append(s);
			return buf.toString();
		}
		
	//生成脚本精灵脚本字符串
	public String makeScriptText(){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<text.length();i++){
			char c = text.charAt(i);
			String temp = String.format("$%x", c).toUpperCase();
			buffer.append(temp);
			if(i!=text.length()-1){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
	//生成导入字符数据
	
	
	//通过$FF 编码列表生成字体图片
	public static void makeFontBitmap(String outDir,String text_unicode) throws FileNotFoundException{
		String[] list = text_unicode.split(",");
		if(!new File(outDir).exists()){
			new File(outDir).mkdir();
		}
		SkyFontTool fontTool = new SkyFontTool(new RandomAccessFile(font_path, "r"));
		for(int i=0;i<list.length;i++){
			String text_u = list[i];
			if(list[i].startsWith("$")){
				text_u = getString_u(list[i].toCharArray());
			}

			char c = text_u.charAt(0);
			
			outChar(fontTool, outDir+File.separator+list[i]+".png", c);
		}
		
		fontTool.xl_font_sky16_close();
		System.out.println("处理完成，共"+list.length+"项");
	}
	
	
	//导出一个文字 参数：fontTool 文件路径 字号
	private static int outChar(SkyFontTool fontTool, String outPath, char c){
		Bitmap bitmap = Bitmap.createBitmap(16, 16, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawColor(0xffffffff);
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
	
	
    //将u码转换为String
    static String getString_u(char text[]) {
        char c;
        int i;
        int ri = 0;
        int type = 0;
        char num = 0;
        StringBuffer rtext = new StringBuffer();
        for (i = 0; i < text.length; i++) {
            switch (type) {
                case 0:
                    if (text[i] == '$')
                        type = 10;
                    else {
                        rtext.append(text[i]);
                        ri++;
                    }
                    break;
                case 1:
                    if (text[i] == 'u')
                        type = 10;
                    else {
                        rtext.append('\\');
                        rtext.append(text[i]);
                        ri++;
                        type = 0;
                    }
                    break;
                case 10:
                case 11:
                case 12:
                case 13:

                    if (text[i] >= '0' && text[i] <= '9') {
                        num = (char) (num * 16 + (text[i] - '0'));
                        type++;
                    } else if (text[i] >= 'a' && text[i] <= 'f') {
                        num = (char) (num * 16 + 10 + (text[i] - 'a'));
                        type++;
                    } 
                    else if (text[i] >= 'A' && text[i] <= 'F') {
                        num = (char) (num * 16 + 10 + (text[i] - 'A'));
                        type++;
                    } 
                    else {
                        rtext.append('\\');
                        rtext.append(num);

                        num = 0;
                        type = 1;
                    }

                    if (type == 14) {
                        rtext.append(num);
                        num = 0;
                        type = 0;
                    }
            }
        }
        return rtext.toString();
    }
	

}
