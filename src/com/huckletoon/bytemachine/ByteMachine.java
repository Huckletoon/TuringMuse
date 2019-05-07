package com.huckletoon.bytemachine;

import java.util.ArrayList;
import java.util.Collections;

public class ByteMachine {
	
	public static Byte BLANK = 0x7f;
	public static Byte[] HEADER_CHUNK = {0x4d, 0x54, 0x68, 0x64, 0x00, 0x00, 0x00, 0x06, 0x00, 0x00, 0x00, 0x01, 0x01, (byte) 0x50};
	public static Byte[] TRACK_CHUNK_HEAD = {0x4d, 0x54, 0x72, 0x6b};
	public static Byte[] END_OF_TRACK = {(byte) 0xff, 0x2f, 0x00};
	
	// Event Arguments
	public static Byte NOTE_ON = (byte) 0x90;
	public static Byte NOTE_OFF = (byte) 0x80;
	public static Byte IMMEDIATE = 0x00;
	public static Byte HALF_NOTE = (byte) 0xf0;
	public static Byte[] WHOLE_NOTE = {(byte) 0x81, (byte)0x70};
	public static Byte FORTE = 0x5f;
	public static Byte OFF_VELOCITY = 0x40;
	
	// Note values
	public static Byte[] NOTE_A = {0x09, 0x15, 0x21, 0x2d, 0x39, 0x45, 0x51, 0x5d, 0x69, 0x75};
	public static Byte[] NOTE_B = {0x0b, 0x17, 0x23, 0x2f, 0x3b, 0x47, 0x53, 0x5f, 0x6b, 0x77};
	public static Byte[] NOTE_C = {0x00, 0x0c, 0x18, 0x24, 0x30, 0x3c, 0x48, 0x54, 0x60, 0x6c, 0x78};
	public static Byte[] NOTE_D = {0x02, 0x0e, 0x1a, 0x26, 0x32, 0x3e, 0x4a, 0x56, 0x62, 0x6e, 0x7a};
	public static Byte[] NOTE_E = {0x04, 0x10, 0x1c, 0x28, 0x34, 0x40, 0x4c, 0x58, 0x64, 0x70, 0x7c};
	public static Byte[] NOTE_F = {0x05, 0x11, 0x1d, 0x29, 0x35, 0x41, 0x4d, 0x59, 0x65, 0x71, 0x7d};
	public static Byte[] NOTE_G = {0x07, 0x13, 0x1f, 0x2b, 0x37, 0x43, 0x4f, 0x5b, 0x67, 0x73, 0x7f};
	
	// Note Index Mod
	public static int NOTE_MOD = 7;
	
	// States
	public static int LOOKING_FOR_ROOT = 0;
	public static int BUILDING_CHORD = 1;
	
	private String inputTape;
	private int headPos;
	private int state;
	private int chordRoot;
	private int octave;
	private boolean isDone;
	private boolean doneOnce;
	private boolean seventhPrev;
	private ArrayList<Byte> byteTape;
	private ArrayList<Byte[]> noteArray;
	
	public ByteMachine() {
		inputTape = "";
		headPos = -1;
		isDone = false;
		doneOnce = false;
		seventhPrev = false;
		byteTape = new ArrayList<>();
		state = LOOKING_FOR_ROOT;
		chordRoot = 0;
		octave = 5;

		noteArray = new ArrayList<>();
		noteArray.add(NOTE_A);
		noteArray.add(NOTE_B);
		noteArray.add(NOTE_C);
		noteArray.add(NOTE_D);
		noteArray.add(NOTE_E);
		noteArray.add(NOTE_F);
		noteArray.add(NOTE_G);
	}
	
	public void loadInput(String tape) {
		inputTape = tape;
		headPos = 0;
		byteTape.clear();
	}
	
	public void finishByte() {
		Collections.addAll(byteTape, WHOLE_NOTE);
		Collections.addAll(byteTape, END_OF_TRACK);
		isDone = true;
	}
	
	public boolean step() {
		if (!isDone && headPos < inputTape.length()) {
			char readChar = inputTape.charAt(headPos);
			lookup(readChar);
			if (!doneOnce) {
				doneOnce = true;
			}
			return true;
		} else if (headPos == inputTape.length()) {
			lookup('a');
			return true;
		} else {
			return false;
		}
	}
	
	private void lookup(char character) {

		if (state == LOOKING_FOR_ROOT) {

			// Note Off
			if (doneOnce) {
				int third = (chordRoot + 2) % 7;
				int fifth = (chordRoot + 4) % 7;

				Collections.addAll(byteTape, WHOLE_NOTE);
				byteTape.add(NOTE_OFF);
				byteTape.add(noteArray.get(chordRoot)[octave]);
				byteTape.add(OFF_VELOCITY);
				
				byteTape.add(IMMEDIATE);
				byteTape.add(NOTE_OFF);
				byteTape.add(noteArray.get(third)[octave]);
				byteTape.add(OFF_VELOCITY);
				
				byteTape.add(IMMEDIATE);
				byteTape.add(NOTE_OFF);
				byteTape.add(noteArray.get(fifth)[octave]);
				byteTape.add(OFF_VELOCITY);
				if (seventhPrev) {
					int seventh = (chordRoot + 6) % 7;
					byteTape.add(IMMEDIATE);
					byteTape.add(NOTE_OFF);
					byteTape.add(noteArray.get(seventh)[octave]);
					byteTape.add(OFF_VELOCITY);
				}
			}
			
			switch(character) {
				case 'a':
					chordRoot = 0;
					break;
				case 'c':
					chordRoot = 2;
					break;
				case 'd':
					chordRoot = 3;
					break;
				case 'e':
					chordRoot = 4;
					break;
				case 'f':
					chordRoot = 5;
					break;
				case 'g':
					chordRoot = 6;
					break;
			}
			headPos++;
			state = BUILDING_CHORD;
			
		} else if (state == BUILDING_CHORD) {
			
			boolean sevFlag = false;
			switch(character) {
				case '+':
				case '-':
				case '7':
					sevFlag = true;
					seventhPrev = true;
					int seventh = (chordRoot + 6) % 7;
					Collections.addAll(byteTape, WHOLE_NOTE);
					byteTape.add(NOTE_ON);
					byteTape.add(noteArray.get(seventh)[octave]);
					byteTape.add(FORTE);
				case 'm':
				case 'M':
					if (!sevFlag) {
						seventhPrev = false;
						Collections.addAll(byteTape, WHOLE_NOTE);
					} else {
						byteTape.add(IMMEDIATE);
					}
					int third = (chordRoot + 2) % 7;
					int fifth = (chordRoot + 4) % 7;
					byteTape.add(NOTE_ON);
					byteTape.add(noteArray.get(chordRoot)[octave]);
					byteTape.add(FORTE);
					
					byteTape.add(IMMEDIATE);
					byteTape.add(NOTE_ON);
					byteTape.add(noteArray.get(third)[octave]);
					byteTape.add(FORTE);
					
					byteTape.add(IMMEDIATE);
					byteTape.add(NOTE_ON);
					byteTape.add(noteArray.get(fifth)[octave]);
					byteTape.add(FORTE);
					break;
			}
			state = LOOKING_FOR_ROOT;
			headPos++;
		}
	}
	
	public ArrayList<Byte> getByteTape() {
		return byteTape;
	}
	
	@Override
	public String toString() {
		String rep = "Input: ";
		for (int x = 0; x < inputTape.length(); x++) {
			if (x == headPos) {
				rep += ">" + inputTape.charAt(x) + "<";
			} else {
				rep += inputTape.charAt(x);
			}
		}
		return rep;
	}

}
