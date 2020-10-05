import java.io.FileNotFoundException;

import com.xl.game.tool.FontFactory;
import com.xl.window.DrawWindow;

public class DrawMain {
	
	public static void main(String[] args) {
//		DrawWindow window= new DrawWindow();
//		window.setVisible(true);
		FontFactory factory = new FontFactory("");
		String text = factory.makeScriptText();
		System.out.println(text);
		
		try {
			factory.makeFontBitmap("D:\\Download\\其它", "$2FF0,$2FF1,$2FF2,$2FF3,$2FF4,$2FF5,$2FF6,$2FF7,$2FF8,$2FF9,$2FFA,$2FFB");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
