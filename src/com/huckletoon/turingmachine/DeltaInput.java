package com.huckletoon.turingmachine;

public class DeltaInput {

	private int state;
	private String character;
	
	public DeltaInput (int st, String ch) {
		state = st;
		character = ch;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int st) {
		state = st;
	}
	
	public String getChar() {
		return character;
	}
	
	public void setChar(String ch) {
		character = ch;
	}
	
	@Override
	public boolean equals(Object other) {
		try {
			if (((DeltaInput)other).getState() == getState() && ((DeltaInput)other).getChar().equals(getChar())) {
				return true;
			}
		} catch (Exception e) {}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (state * 256) + character.hashCode();
	}
	
}
