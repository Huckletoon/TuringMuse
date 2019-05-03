package com.huckletoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.huckletoon.turingmachine.DeltaInput;
import com.huckletoon.turingmachine.DeltaOutput;
import com.huckletoon.turingmachine.TuringMachine;

public class TMDriver {

	public static boolean RIGHT = DeltaOutput.RIGHT;
	public static boolean LEFT = DeltaOutput.LEFT;
	public static String BLANK = TuringMachine.BLANK;
	
	public static void main(String[] args) {
		
		// Initialize variables
		TuringMachine tm = new TuringMachine();
		Map<DeltaInput, DeltaOutput> lookupTable;
		ArrayList<Integer> states = new ArrayList<Integer>();
		ArrayList<Integer> accepting = new ArrayList<Integer>();
		List<String> tape = new ArrayList<String>();
		
		/***************
		 * Bitwise AND *
		 ***************/
		// Prepare data for machine
		lookupTable = createAndTable();
		for (int n = 0; n < 9; n++) {
			states.add(n);
		}
		accepting.add(8);
		// Encoded :  0 1 1 1 0 0 1 1 
		//          & 1 0 1 0 1 0 1 0
		// Expected:  0 0 1 0 0 0 1 0
		String str = "a,b,b,b,a,a,b,b,&,b,a,b,a,b,a,b,a";
		String temp[] = str.split(",");
		tape = new ArrayList<>(Arrays.asList(temp));
		
		// Load Machine Data
		tm.resetMachine();
		tm.loadTable(lookupTable);
		tm.loadStates(states);
		tm.loadAccepting(accepting);
		tm.loadTape((ArrayList<String>) tape);
		
//		System.out.println(tm);
//		while (tm.isReady() && !tm.isHalted() && tm.step()) {
//			System.out.println(tm);
//		}
//		System.out.print("Final Tape: " + tm.getTape());
		
		/*******************
		 * Binary Addition *
		 *******************/
		//Prepare data for machine
		lookupTable = createAddTable();
		states.clear();
		for (int n = 0; n < 4; n++) {
			states.add(n);
		}
		accepting.clear();
		accepting.add(2);
		// Encoded : 0 1 1 0 1 1 0 1
		//         + 0 0 1 1 1 1 1 0
		// Expected: 1 0 1 0 1 0 1 1
		str = "a,b,d,c,d,d,c,b";
		temp = str.split(",");
		tape = new ArrayList<>(Arrays.asList(temp));
		//Load Machine Data
		tm.resetMachine();
		tm.loadTable(lookupTable);
		tm.loadStates(states);
		tm.loadAccepting(accepting);
		tm.loadTape((ArrayList<String>)tape);
//		System.out.println(tm);
//		while (tm.isReady() && !tm.isHalted() && tm.step()) {
//			System.out.println(tm);
//		}
//		System.out.print("Final Tape: " + tm.getTape());

		/*************
		 * Increment *
		 *************/
		//Prepare data for machine
		lookupTable = createIncremTable();
		states.clear();
		for (int n = 0; n < 5; n++) {
			states.add(n);
		}
		accepting.clear();
		accepting.add(4);
		// Input:    1 1 1 1 1 1 1 1
		// Expected: 1 0 0 0 0 0 0 0 0
		str = "1,1,1,1,1,1,1,1";
		temp = str.split(",");
		tape = new ArrayList<>(Arrays.asList(temp));
		
		//Load Machine Data
		tm.resetMachine();
		tm.loadTable(lookupTable);
		tm.loadStates(states);
		tm.loadAccepting(accepting);
		tm.loadTape((ArrayList<String>)tape);
		
//		System.out.println(tm);
//		while (tm.isReady() && !tm.isHalted() && tm.step()) {
//			System.out.println(tm);
//		}
//		System.out.print("Final Tape: " + tm.getTape());
		
		/*************
		 * Decrement *
		 *************/
		//Prepare data for machine
		lookupTable = createDecremTable();
		states.clear();
		for (int n = 0; n < 5; n++) {
			states.add(n);
		}
		accepting.clear();
		accepting.add(4);
		accepting.add(2);
		// Input:    1 0 1 1 0 1 1 0
		// Expected: 1 0 1 1 0 1 0 1
		str = "1,0,1,1,0,1,1,0";
		temp = str.split(",");
		tape = new ArrayList<>(Arrays.asList(temp));
		
		//Load Machine Data
		tm.resetMachine();
		tm.loadTable(lookupTable);
		tm.loadStates(states);
		tm.loadAccepting(accepting);
		tm.loadTape((ArrayList<String>)tape);
		
//		System.out.println(tm);
//		while (tm.isReady() && !tm.isHalted() && tm.step()) {
//			System.out.println(tm);
//		}
//		System.out.print("Final Tape: " + tm.getTape());
		
		/*************
		 * RoundDown *
		 *************/
		//Prepare data for machine
		lookupTable = createRndDwnTable();
		states.clear();
		for (int n = 0; n < 3; n++) {
			states.add(n);
		}
		accepting.clear();
		accepting.add(2);
		// Input:    1 0 1 1 0 1 1 1
		// Expected: 1 0 1 1 0 1 1 0
		str = "1,0,1,1,0,1,1,1";
		temp = str.split(",");
		tape = new ArrayList<>(Arrays.asList(temp));
		
		//Load Machine Data
		tm.resetMachine();
		tm.loadTable(lookupTable);
		tm.loadStates(states);
		tm.loadAccepting(accepting);
		tm.loadTape((ArrayList<String>)tape);
		
//		System.out.println(tm);
//		while (tm.isReady() && !tm.isHalted() && tm.step()) {
//			System.out.println(tm);
//		}
//		System.out.print("Final Tape: " + tm.getTape());
		
		/******************
		 * Multiplication *
		 ******************/
		//Prepare data for machine
		lookupTable = createMultTable();
		states.clear();
		for (int n = 0; n < 6; n++) {
			states.add(n);
		}
		accepting.clear();
		accepting.add(5);
		// Input:    1 1 1
		// Expected: 1 1 1 1 1 1
		str = "1,1,1";
		temp = str.split(",");
		tape = new ArrayList<>(Arrays.asList(temp));
		
		//Load Machine Data
		tm.resetMachine();
		tm.loadTable(lookupTable);
		tm.loadStates(states);
		tm.loadAccepting(accepting);
		tm.loadTape((ArrayList<String>)tape);
		
//		System.out.println(tm);
//		while (tm.isReady() && !tm.isHalted() && tm.step()) {
//			System.out.println(tm);
//		}
//		System.out.print("Final Tape: " + tm.getTape());
		
		/*******************
		 * count-to-binary *
		 *******************/
		//Prepare data for machine
		lookupTable = createBinCountTable();
		states.clear();
		for (int n = 0; n < 10; n++) {
			states.add(n);
		}
		accepting.clear();
		accepting.add(9);
		// Input:    1 1 1 1 1 1 1
		// Expected: 1 1 1
		str = "1,1,1,1,1,1,1";
		temp = str.split(",");
		tape = new ArrayList<>(Arrays.asList(temp));
		
		//Load Machine Data
		tm.resetMachine();
		tm.loadTable(lookupTable);
		tm.loadStates(states);
		tm.loadAccepting(accepting);
		tm.loadTape((ArrayList<String>)tape);
		
		System.out.println(tm);
		while (tm.isReady() && !tm.isHalted() && tm.step()) {
			System.out.println(tm);
		}
		System.out.print("Final Tape: " + tm.getTape());
		
		
	}
	
	// Lookup table for bitwise AND.
	// 0 and 1 are encoded as such: a=0, b=1.
	// Output of this table should be a string comprised of 0s and 1s.
	// Accepting state: 8
	public static Map<DeltaInput, DeltaOutput> createAndTable() {
		Hashtable<DeltaInput, DeltaOutput> table = new Hashtable<DeltaInput, DeltaOutput>();
		table.put(new DeltaInput(0, "a"), new DeltaOutput(0, "a", RIGHT));
		table.put(new DeltaInput(0, "b"), new DeltaOutput(0, "b", RIGHT));
		table.put(new DeltaInput(0, "&"), new DeltaOutput(0, "&", RIGHT));
		table.put(new DeltaInput(0, BLANK), new DeltaOutput(1, BLANK, LEFT));
		table.put(new DeltaInput(1, "a"), new DeltaOutput(2, BLANK, LEFT));
		table.put(new DeltaInput(1, "b"), new DeltaOutput(5, BLANK, LEFT));
		table.put(new DeltaInput(1, "&"), new DeltaOutput(7, BLANK, LEFT));
		table.put(new DeltaInput(2, "a"), new DeltaOutput(2, "a", LEFT));
		table.put(new DeltaInput(2, "b"), new DeltaOutput(2, "b", LEFT));
		table.put(new DeltaInput(2, "&"), new DeltaOutput(3, "&", LEFT));
		table.put(new DeltaInput(3, "0"), new DeltaOutput(3, "0", LEFT));
		table.put(new DeltaInput(3, "1"), new DeltaOutput(3, "1", LEFT));
		table.put(new DeltaInput(3, BLANK), new DeltaOutput(4, "0", RIGHT));
		table.put(new DeltaInput(3, "a"), new DeltaOutput(4, "0", RIGHT));
		table.put(new DeltaInput(3, "b"), new DeltaOutput(4, "0", RIGHT));
		table.put(new DeltaInput(4, "0"), new DeltaOutput(4, "0", RIGHT));
		table.put(new DeltaInput(4, "1"), new DeltaOutput(4, "1", RIGHT));
		table.put(new DeltaInput(4, "a"), new DeltaOutput(4, "a", RIGHT));
		table.put(new DeltaInput(4, "b"), new DeltaOutput(4, "b", RIGHT));
		table.put(new DeltaInput(4, "&"), new DeltaOutput(4, "&", RIGHT));
		table.put(new DeltaInput(4, BLANK), new DeltaOutput(1, BLANK, LEFT));
		table.put(new DeltaInput(5, "a"), new DeltaOutput(5, "a", LEFT));
		table.put(new DeltaInput(5, "b"), new DeltaOutput(5, "b", LEFT));
		table.put(new DeltaInput(5, "&"), new DeltaOutput(6, "&", LEFT));
		table.put(new DeltaInput(6, "0"), new DeltaOutput(6, "0", LEFT));
		table.put(new DeltaInput(6, "1"), new DeltaOutput(6, "1", LEFT));
		table.put(new DeltaInput(6, "a"), new DeltaOutput(4, "0", RIGHT));
		table.put(new DeltaInput(6, "b"), new DeltaOutput(4, "1", RIGHT));
		table.put(new DeltaInput(6, BLANK), new DeltaOutput(4, "0", RIGHT));
		table.put(new DeltaInput(7, "0"), new DeltaOutput(7, "0", LEFT));
		table.put(new DeltaInput(7, "1"), new DeltaOutput(7, "1", LEFT));
		table.put(new DeltaInput(7, "a"), new DeltaOutput(7, "0", LEFT));
		table.put(new DeltaInput(7, "b"), new DeltaOutput(7, "0", LEFT));
		table.put(new DeltaInput(7, BLANK), new DeltaOutput(8, BLANK, RIGHT));
		return table;
	}
	
	// Lookup table for binary addition.
	// I chose to forego the stacker since I modified it to make the AND table.
	// And I'm too lazy to modify it again.
	// Thus, the stacked symbols are encoded as thus:
	// 		0/0=a, 1/0=b, 0/1=c, 1/1=d
	// Output of this table should be a string comprised of 0s and 1s.
	// Accepting state: 2
	public static Map<DeltaInput, DeltaOutput> createAddTable() {
		Hashtable<DeltaInput, DeltaOutput> table = new Hashtable<DeltaInput, DeltaOutput>();
		table.put(new DeltaInput(0, "a"), new DeltaOutput(0, "a", RIGHT));
		table.put(new DeltaInput(0, "b"), new DeltaOutput(0, "b", RIGHT));
		table.put(new DeltaInput(0, "c"), new DeltaOutput(0, "c", RIGHT));
		table.put(new DeltaInput(0, "d"), new DeltaOutput(0, "d", RIGHT));
		table.put(new DeltaInput(0, BLANK), new DeltaOutput(1, BLANK, LEFT));
		table.put(new DeltaInput(1, "a"), new DeltaOutput(1, "0", LEFT));
		table.put(new DeltaInput(1, "b"), new DeltaOutput(1, "1", LEFT));
		table.put(new DeltaInput(1, "c"), new DeltaOutput(1, "1", LEFT));
		table.put(new DeltaInput(1, BLANK), new DeltaOutput(2, BLANK, RIGHT));
		table.put(new DeltaInput(1, "d"), new DeltaOutput(3, "0", LEFT));
		table.put(new DeltaInput(3, "d"), new DeltaOutput(3, "1", LEFT));
		table.put(new DeltaInput(3, "c"), new DeltaOutput(3, "0", LEFT));
		table.put(new DeltaInput(3, "b"), new DeltaOutput(3, "0", LEFT));
		table.put(new DeltaInput(3, "a"), new DeltaOutput(1, "1", LEFT));
		table.put(new DeltaInput(3, BLANK), new DeltaOutput(1, "1", LEFT));
		return table;
	}
	
	// Lookup table for incrementing a binary number.
	// Output of this table should be a string comprised of 0s and 1s.
	// Accepting state: 4
	public static Map<DeltaInput, DeltaOutput> createIncremTable() {
		Hashtable<DeltaInput, DeltaOutput> table = new Hashtable<DeltaInput, DeltaOutput>();
		table.put(new DeltaInput(0, "1"), new DeltaOutput(0, "1", RIGHT));
		table.put(new DeltaInput(0, "0"), new DeltaOutput(0, "0", RIGHT));
		table.put(new DeltaInput(0, BLANK), new DeltaOutput(1, BLANK, LEFT));
		table.put(new DeltaInput(1, "0"), new DeltaOutput(2, "1", LEFT));
		table.put(new DeltaInput(1, "1"), new DeltaOutput(3, "0", LEFT));
		table.put(new DeltaInput(2, "0"), new DeltaOutput(2, "0", LEFT));
		table.put(new DeltaInput(2, "1"), new DeltaOutput(2, "1", LEFT));
		table.put(new DeltaInput(2, BLANK), new DeltaOutput(4, BLANK, RIGHT));
		table.put(new DeltaInput(3, "0"), new DeltaOutput(2, "1", LEFT));
		table.put(new DeltaInput(3, "1"), new DeltaOutput(3, "0", LEFT));
		table.put(new DeltaInput(3, BLANK), new DeltaOutput(2, "1", LEFT));
		return table;
	}
	
	// Lookup table for decrementing a binary number.
	// Accepting state: 2, 4
	public static Map<DeltaInput, DeltaOutput> createDecremTable() {
		Hashtable<DeltaInput, DeltaOutput> table = new Hashtable<DeltaInput, DeltaOutput>();
		table.put(new DeltaInput(0, "0"), new DeltaOutput(0, "0", RIGHT));
		table.put(new DeltaInput(0, "1"), new DeltaOutput(0, "1", RIGHT));
		table.put(new DeltaInput(0, BLANK), new DeltaOutput(1, BLANK, LEFT));
		table.put(new DeltaInput(1, "0"), new DeltaOutput(1, "1", LEFT));
		table.put(new DeltaInput(1, "1"), new DeltaOutput(3, "0", LEFT));
		table.put(new DeltaInput(1, BLANK), new DeltaOutput(0, "1", RIGHT));
		table.put(new DeltaInput(3, "0"), new DeltaOutput(3, "0", LEFT));
		table.put(new DeltaInput(3, "1"), new DeltaOutput(3, "1", LEFT));
		table.put(new DeltaInput(3, BLANK), new DeltaOutput(4, BLANK, RIGHT));
		return table;
	}
	
	// Lookup table for RoundDown as explained in exercise 4.
	// Accepting state: 2
	public static Map<DeltaInput, DeltaOutput> createRndDwnTable() {
		Hashtable<DeltaInput, DeltaOutput> table = new Hashtable<DeltaInput, DeltaOutput>();
		table.put(new DeltaInput(0, "0"), new DeltaOutput(0, "0", RIGHT));
		table.put(new DeltaInput(0, "1"), new DeltaOutput(0, "1", RIGHT));
		table.put(new DeltaInput(0, BLANK), new DeltaOutput(1, BLANK, LEFT));
		table.put(new DeltaInput(1, "0"), new DeltaOutput(2, "0", RIGHT));
		table.put(new DeltaInput(1, "1"), new DeltaOutput(2, "0", RIGHT));
		table.put(new DeltaInput(1, BLANK), new DeltaOutput(2, "0", RIGHT));
		
		return table;
	}
	
	// Lookup table to multiply a given unary number by 2.
	// My method uses 'a' to mark 1s, then runs to the right and adds a 0.
	// After running through all the 1s, the tape should have all a's and 0s.
	// These are then rewritten as 1s and we're golden.
	// Accepting state: 5
	public static Map<DeltaInput, DeltaOutput> createMultTable() {
		Hashtable<DeltaInput, DeltaOutput> table = new Hashtable<DeltaInput, DeltaOutput>();
		table.put(new DeltaInput(0, "1"), new DeltaOutput(1, "a", RIGHT));
		table.put(new DeltaInput(0, "0"), new DeltaOutput(3, "0", LEFT));
		table.put(new DeltaInput(0, BLANK), new DeltaOutput(0, BLANK, RIGHT));
		table.put(new DeltaInput(1, "0"), new DeltaOutput(1, "0", RIGHT));
		table.put(new DeltaInput(1, "1"), new DeltaOutput(1, "1", RIGHT));
		table.put(new DeltaInput(1, BLANK), new DeltaOutput(2, "0", RIGHT));
		table.put(new DeltaInput(2, "0"), new DeltaOutput(2, "0", LEFT));
		table.put(new DeltaInput(2, "1"), new DeltaOutput(2, "1", LEFT));
		table.put(new DeltaInput(2, BLANK), new DeltaOutput(2, BLANK, LEFT));
		table.put(new DeltaInput(2, "a"), new DeltaOutput(0, "a", RIGHT));
		table.put(new DeltaInput(3, "0"), new DeltaOutput(3, "0", LEFT));
		table.put(new DeltaInput(3, "a"), new DeltaOutput(3, "a", LEFT));
		table.put(new DeltaInput(3, BLANK), new DeltaOutput(4, BLANK, RIGHT));
		table.put(new DeltaInput(4, "0"), new DeltaOutput(4, "1", RIGHT));
		table.put(new DeltaInput(4, "a"), new DeltaOutput(4, "1", RIGHT));
		table.put(new DeltaInput(4, BLANK), new DeltaOutput(5, BLANK, LEFT));
		return table;
	}
	
	// Lookup table to perform count-to-binary (unary-to-binary conversion).
	// My method begins with moving left one character and marking a #.
	// This serves as an indicator for the start of the binary string.
	// The machine should then move right and mark the first 1 it sees with an 'x'.
	// Lastly, it runs left and increments the binary string before repeating the process.
	// Accepting state: 9
	public static Map<DeltaInput, DeltaOutput> createBinCountTable() {
		Hashtable<DeltaInput, DeltaOutput> table = new Hashtable<DeltaInput, DeltaOutput>();
		table.put(new DeltaInput(0, "1"), new DeltaOutput(1, "1", LEFT));
		table.put(new DeltaInput(1, BLANK), new DeltaOutput(2, "#", RIGHT));
		table.put(new DeltaInput(2, "1"), new DeltaOutput(3, "x", LEFT));
		table.put(new DeltaInput(2, "x"), new DeltaOutput(2, "x", RIGHT));
		table.put(new DeltaInput(2, BLANK), new DeltaOutput(7, BLANK, LEFT));
		table.put(new DeltaInput(3, "x"), new DeltaOutput(3, "x", LEFT));
		table.put(new DeltaInput(3, "#"), new DeltaOutput(4, "#", LEFT));
		table.put(new DeltaInput(4, "1"), new DeltaOutput(6, "0", LEFT));
		table.put(new DeltaInput(4, "0"), new DeltaOutput(5, "1", RIGHT));
		table.put(new DeltaInput(4, BLANK), new DeltaOutput(5, "1", RIGHT));
		table.put(new DeltaInput(5, "1"), new DeltaOutput(5, "1", RIGHT));
		table.put(new DeltaInput(5, "0"), new DeltaOutput(5, "0", RIGHT));
		table.put(new DeltaInput(5, "#"), new DeltaOutput(2, "#", RIGHT));
		table.put(new DeltaInput(6, "1"), new DeltaOutput(6, "0", LEFT));
		table.put(new DeltaInput(6, "0"), new DeltaOutput(5, "1", RIGHT));
		table.put(new DeltaInput(6, BLANK), new DeltaOutput(5, "1", RIGHT));
		table.put(new DeltaInput(7, "x"), new DeltaOutput(7, BLANK, LEFT));
		table.put(new DeltaInput(7, "#"), new DeltaOutput(8, BLANK, LEFT));
		table.put(new DeltaInput(8, "1"), new DeltaOutput(8, "1", LEFT));
		table.put(new DeltaInput(8, "0"), new DeltaOutput(8, "0", LEFT));
		table.put(new DeltaInput(8, BLANK), new DeltaOutput(9, BLANK, RIGHT));
		return table;
	}
	
}
