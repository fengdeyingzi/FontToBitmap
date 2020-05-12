package com.xl.window;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.xl.view.CirColorView;
import com.xl.view.DrawView;

import android.content.Context;

public class DrawWindow extends JFrame{
	DrawView drawView;
	
	public DrawWindow() {
		// TODO Auto-generated constructor stub
		setTitle("绘图练习");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		JPanel mainJPanel= new JPanel();
		setContentPane(mainJPanel);
		setLayout(new BoxLayout(mainJPanel, BoxLayout.Y_AXIS));
		drawView= new DrawView();
		drawView.setPreferredSize(new Dimension(320, 320));
		
		
		getContentPane().add(drawView);
		CirColorView colorView = new CirColorView(new Context());
		colorView.setColor(0x8050f050);
		colorView.setCirSize(8);
		colorView.setPadding(8);
		colorView.setScrollX(50);
		colorView.setScrollY(30);
//		colorView.setCirColor(0x8060a0f0);
		colorView.setPreferredSize(new Dimension(64, 64));
		getContentPane().add(colorView);
		
	}

}
