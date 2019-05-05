package com.huckletoon.bytemachine;

public class DeltaOutputByte {
	
	public static boolean RIGHT = true;
	public static boolean LEFT = false;
	
	private int state;
	private Byte character;
	private boolean direction;
	
	public DeltaOutputByte (int st, Byte ch, boolean dir) {
		state = st;
		character = ch;
		direction = dir;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int st) {
		state = st;
	}
	
	public Byte getChar() {
		return character;
	}
	
	public void setChar(Byte ch) {
		character = ch;
	}
	
	public boolean getDirection() {
		return direction;
	}
	
	public void setDirection(boolean dir) {
		direction = dir;
	}
	

}
