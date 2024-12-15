import java.io.FileNotFoundException;

import com.xl.game.tool.FontFactory;
import com.xl.window.DrawWindow;

public class DrawMain {
	static String outDir = "crop";
	static String text_un = null; //"你,好,呀,$2FF0";
	static String helpText = " -text 输入字体\r\n -o 输出目录\r\n -ztext 要转换的text";
	static String text_zh = null;
	public static void main(String[] args) {
//		DrawWindow window= new DrawWindow();
//		window.setVisible(true);

String type = "";
		if(args.length == 0){

				System.out.println("font2bitmap - 1.0");
				System.out.println(helpText);
				System.out.println("请输入命令, -h 可查看帮助");


		}else {
			for (int i = 0; i < args.length; i++) {
				String item = args[i];
				if (item.startsWith("-")) {
					type = item.substring(1);
				} else {
					if (type.equals("text")) {
						text_un = item;
					} else if (type.equals("o")) {
						outDir = item;
					}else if(type.equals("ztext")){
						text_zh = item;
					}
				}
			}
		}
		if(type.equals("h")){
			System.out.println(helpText);
			System.out.println("风的影子 制作");
			System.out.println("当前运行在 "+System.getProperty("java.vm.specification.name"));
		}
//						inputList.add(item);


		FontFactory factory = new FontFactory("");
		String text = factory.makeScriptText();
		System.out.println(text);
		if(text_zh!=null){
			StringBuilder builder = new StringBuilder();
			for(int i=0;i<text_zh.length();i++){
				builder.append(charToHex(text_zh.charAt(i)));
				if(i!=text_zh.length()-1){
					builder.append(",");
				}
			}
			System.out.println(builder);
		}
		try {
			if(text_un!=null){
				if((!text_un.contains(",") && text_un.length()>5) || (getCharCount(text_un, ',')<2 && text_un.length()>15)){
					StringBuilder builder = new StringBuilder();
					for(int i=0;i<text_un.length();i++){
						builder.append(charToHex(text_un.charAt(i)));
						if(i!=text_un.length()-1){
							builder.append(",");
						}
					}
					text_un = builder.toString();
				}
				factory.makeFontBitmap(outDir, text_un);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String charToHex(char c) {
		// 获取字符的Unicode值
		int unicodeValue = c;
		// 将Unicode值转换为十六进制字符串，并填充至4位
		String hexString = String.format("$%04X", unicodeValue);
		return hexString;
	}

	public static int getCharCount(String str, char charToCount){
		int count = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == charToCount) {
				count++;
			}
		}
		return count;
	}

}
