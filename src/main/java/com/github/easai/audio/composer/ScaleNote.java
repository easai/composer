package com.github.easai.audio.composer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

public class ScaleNote {
	public static ArrayList<MidiDevice> getDevices() {
		ArrayList<MidiDevice> devices = new ArrayList<MidiDevice>();

		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for (int i = 0; i < infos.length; i++) {
			MidiDevice.Info info = infos[i];
			MidiDevice dev = null;
			try {
				dev = MidiSystem.getMidiDevice(info);
				devices.add(dev);
			} catch (SecurityException e) {
				System.err.println(e.getMessage());
			} catch (MidiUnavailableException e) {
				System.err.println(e.getMessage());
			}
		}
		return devices;
	}

	public static void dumpDeviceInfo() {
		ArrayList<MidiDevice> devices = getDevices();

		for (int i = 0; i < devices.size(); i++) {
			MidiDevice device = devices.get(i);
			MidiDevice.Info info = device.getDeviceInfo();
			System.out.println("[" + i + "] devinfo: " + info.toString());
			System.out.println("  name:" + info.getName());
			System.out.println("  vendor:" + info.getVendor());
			System.out.println("  version:" + info.getVersion());
			System.out.println("  description:" + info.getDescription());
			if (device instanceof Synthesizer) {
				System.out.println("  SYNTHESIZER");
			}
			if (device instanceof Sequencer) {
				System.out.println("  SEQUENCER");
			}
			System.out.println("");
		}
	}

	public static void main(String args[]) {
		try {

			Receiver receiver = MidiSystem.getReceiver();
			ShortMessage message = new ShortMessage();

			message.setMessage(ShortMessage.NOTE_ON, 60, 127);
			receiver.send(message, -1);

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			reader.readLine();
			/*
			 * dumpDeviceInfo();
			 * 
			 * Synthesizer synth=MidiSystem.getSynthesizer(); synth.open();
			 * MidiChannel[] mc=synth.getChannels(); Instrument[]
			 * instr=synth.getDefaultSoundbank().getInstruments();
			 * synth.loadInstrument(instr[0]); mc[3].noteOn(60,600);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
