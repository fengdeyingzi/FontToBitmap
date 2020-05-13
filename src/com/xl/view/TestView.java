package com.xl.view;

import java.io.IOException;

import com.xl.graphics.Bitmap;
import com.xl.graphics.BitmapFactory;
import com.xl.graphics.Canvas;

import android.content.Context;
import android.view.View;

public class TestView extends View{

	public TestView(Context context) {
		super(context);
		initView();
	}
	
	private void initView(){
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(getContext().getResources().getAssets().open("icon.png"));
			canvas.drawBitmap(bitmap, 20, 20, null);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		super.onDraw(canvas);
		
	}

}
