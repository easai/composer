package com.github.easai.audio.composer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import com.github.easai.audio.composer.KeyboardMenu.MENUITEM;

public class Keyboard extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	KeyboardPanel keyboardPanel = new KeyboardPanel();
	KeyboardMenu menu = new KeyboardMenu();
	JTextArea score=new JTextArea();

	Keyboard() {

		JSplitPane vertSplit=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vertSplit.setDividerLocation(150);
		vertSplit.setTopComponent(keyboardPanel);
		vertSplit.setBottomComponent(score);
		
		getContentPane().add(vertSplit, BorderLayout.CENTER);
		
		menu.setMenu(this, this, Locale.US);

		setSize(1300, 400);
		setTitle("Keyboard");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void about(){
		JOptionPane.showMessageDialog(this, "Keyboard\n Copyright 2017 easai\n All rights reserved.");
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		MENUITEM n = menu.comp.get(source);
		if (n != null) {
			switch (n) {
			case nFileOpen:
				break;
			case nFileSave:
				break;
			case nFileSaveAs:
				break;
			case nFileQuit:
				dispose();
				break;
			case nPlayTest:
				keyboardPanel.play(keyboardPanel.test);
				break;
			case nHelpAbout:
				about();
				break;
			}
		}
	}

	public static void main(String[] args) {
		new Keyboard();
	}
}
