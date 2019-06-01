package com.xl.window;

import javax.swing.JFrame;

import com.xl.view.DrawView;

public class DrawWindow extends JFrame{
	DrawView drawView;
	
	public DrawWindow() {
		// TODO Auto-generated constructor stub
		setTitle("绘图练习");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		drawView= new DrawView();
		getContentPane().add(drawView);
		
	}

}
