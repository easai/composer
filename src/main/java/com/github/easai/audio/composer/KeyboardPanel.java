package com.github.easai.audio.composer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.swing.JButton;
import javax.swing.JPanel;

public class KeyboardPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int chordI = 0; // tonic
	final int chordIV = 5; // dominant
	final int chordV = 7; // subdominant

	Receiver receiver;
	ShortMessage message;
	String scale[] = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "c", "c#", "d", "d#", "e", "f",
			"f#", "g", "g#", "a", "a#", "b" };
	// String test[]={"C","F","E","G","F","D","C"};
	// String test[]={"C"};
	String test[] = { "G", "D", "E", "D", "E", "F#", "G", "C#", "B", "E", "D", "E", "C#", "B" };

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		int note = 60;

		int i = 0;
		while (!scale[i].equals(command) && ++i < scale.length)
			;

		if (i < scale.length) {
			note = 60 + i;
		}
		try {
			playChord(note);

			// message.setMessage(ShortMessage.NOTE_ON, note, 127);
			// receiver.send(message, -1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public KeyboardPanel() {
		try {
			receiver = MidiSystem.getReceiver();
			message = new ShortMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		setLayout(new GridLayout());

		JButton button[] = new JButton[scale.length];
		int buttonWidth = 50;
		int buttonHeight = 500;
		for (int i = 0; i < scale.length; i++) {
			button[i] = new JButton(scale[i]);
			if (scale[i].endsWith("#")) {
				button[i].setBackground(Color.black);
				button[i].setForeground(Color.white);
				button[i].setPreferredSize(new Dimension(buttonWidth, buttonHeight / 3));
			} else {
				button[i].setBackground(Color.white);
				button[i].setForeground(Color.black);
				button[i].setPreferredSize(new Dimension(buttonWidth, buttonHeight));
			}
			add(button[i]);
			button[i].addActionListener(this);
		}

		//play(test);
		/*
		 * try { AudioFormat linear=new AudioFormat(44100,8,1,true,false);
		 * DataLine.Info info=new DataLine.Info(TargetDataLine.class,linear);
		 * TargetDataLine target=(TargetDataLine)AudioSystem.getLine(info);
		 * target.open(linear); target.start(); AudioInputStream stream=new
		 * AudioInputStream(target);
		 * 
		 * int a=30; byte[] data=new byte[44100*8/8*1*a];
		 * 
		 * 
		 * 
		 * System.out.println("reading"); stream.read(data,0,data.length);
		 * 
		 * 
		 * target.stop(); target.close();
		 * 
		 * AudioFormat frmt=new AudioFormat(44100,8,1,true,false); DataLine.Info
		 * info2=new DataLine.Info(Clip.class,frmt); Clip
		 * clip=(Clip)AudioSystem.getLine(info2);
		 * clip.open(frmt,data,0,data.length);
		 * 
		 * clip.start();
		 * 
		 * while(clip.isRunning()){ Thread.sleep(100); } } catch(Exception e) {
		 * }
		 */
		// for(int i=0;i<10;i++)
		// play(test);
		/*
		 * String test[]=new String[str.length()]; for(int
		 * i=0;i<str.length();i++) test[i]=""+str.charAt(i); playMelody(test);
		 */
	}

	public void play(String[] melody) {
		int shift[] = { 0, 12, 5, 17, 5, 17, 5, 7, 19, 19, 19, 7, 19, 19, 19, 12, 12, 12, 12, 12, 12 };

		try {
			Thread.sleep(500);
			for (int k = 0; k < shift.length; k++) {
				for (int i = 0; i < melody.length; i++) {
					Thread.sleep(100);
					int note = 60;
					int j = 0;
					while (!scale[j].equals(melody[i]) && ++j < scale.length)
						;
					if (j < scale.length)
						note = 60 + j + shift[k];
					playChord(note);
				}
				Thread.sleep(190);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void playChord(int note) {
		try {
			message.setMessage(ShortMessage.NOTE_ON, note, 127);
			receiver.send(message, -1);

			message.setMessage(ShortMessage.NOTE_ON, note + 4, 127);
			receiver.send(message, -1);
			message.setMessage(ShortMessage.NOTE_ON, note + 7, 127);
			receiver.send(message, -1);

			// More tonic

			message.setMessage(ShortMessage.NOTE_ON, note + 12, 127);
			receiver.send(message, -1);
			message.setMessage(ShortMessage.NOTE_ON, note + 24, 127);
			receiver.send(message, -1);
			message.setMessage(ShortMessage.NOTE_ON, note + 36, 127);
			receiver.send(message, -1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playMelody(String str) {
		String test[] = new String[str.length()];
		for (int i = 0; i < str.length(); i++)
			test[i] = "" + str.charAt(i);
		playMelody(test);
		play(test);
	}

	public void playMelody(String[] melody) {
		try {
			Thread.sleep(500);
			for (int i = 0; i < melody.length; i++) {
				Thread.sleep(300);
				int note = 60;
				int j = 0;
				while (!scale[j].equals(melody[i]) && ++j < scale.length)
					;
				if (j == 4)
					j = 3;
				if (j < scale.length)
					note = 60 + j;
				message.setMessage(ShortMessage.NOTE_ON, note, 127);
				receiver.send(message, -1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
