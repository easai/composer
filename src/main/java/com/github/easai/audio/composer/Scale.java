package com.github.easai.audio.composer;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class Scale {
	public static void main(String args[]) {
		try {

			Receiver receiver = MidiSystem.getReceiver();
			ShortMessage message = new ShortMessage();

			int major[] = { 1, 1, 0, 1, 1, 1, 0 };
			int minor[] = { 1, 0, 1, 1, 0, 1, 1 };

			for (int j = 0; j < 12; j++) {
				int note = 60 + j;
				Thread.sleep(500);
				message.setMessage(ShortMessage.NOTE_ON, note, 127);
				receiver.send(message, -1);
				for (int i = 0; i < major.length; i++) {
					Thread.sleep(500);
					note += minor[i] + 1;
					message.setMessage(ShortMessage.NOTE_ON, note, 127);
					receiver.send(message, -1);
				}
				Thread.sleep(500);
			}

			// BufferedReader reader = new BufferedReader(new
			// InputStreamReader(System.in));
			// reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
