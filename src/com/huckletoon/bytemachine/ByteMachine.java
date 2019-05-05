package com.huckletoon.bytemachine;

import com.huckletoon.turingmachine.DeltaInput;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Hashtable;

public class ByteMachine {
	
	public static Byte BLANK = 0x7f;
	
	private Map<DeltaInput, DeltaOutputByte> lookupTable;
	private ArrayList<Integer> states;
	private ArrayList<Integer> accepting;
	private ArrayList<String> tape;
	private int headPos;
	private int currentState;
	private boolean ready;
	private boolean halted;
	private DeltaInput nextStep;
	private DeltaOutputByte stepResult;
	
	

}
