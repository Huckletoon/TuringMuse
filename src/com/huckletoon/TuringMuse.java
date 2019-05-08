package com.huckletoon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.huckletoon.bytemachine.ByteMachine;
import com.huckletoon.grammar.GrammarMachine;
import com.huckletoon.grammar.GrammarProduction;

public class TuringMuse {
	
	static String file = "temp.mid";
	
	public static void main(String[] args) throws IOException {
		
		// Initialize Grammar Machine
		GrammarMachine gm = new GrammarMachine();
		List<GrammarProduction> prods = buildDorianD();
		for (int x = 0; x < prods.size(); x++) {
			gm.addProduction(prods.get(x));
		}
		
		// Run Grammar
		gm.loadSeed("E");
		gm.runGrammar();
		String chordProgression = gm.getString();
		System.out.println(chordProgression);
		
		// Initialize ByteMachine
		ByteMachine bMachine = new ByteMachine();
		bMachine.loadInput(chordProgression);
		// Run and finish machine
		System.out.println(bMachine);
		while (bMachine.step()) {
			System.out.println(bMachine);
		}
		bMachine.finishByte();
		
		// Gather all data together into valid MIDI format
		ArrayList<Byte> musicData = new ArrayList<>();
		int dataSize = bMachine.getByteTape().size();
		int padding = 0;
		if (dataSize < 16777216) { padding++; }
		if (dataSize < 65536) { padding++; }
		if (dataSize < 256) { padding++; }
		Collections.addAll(musicData, ByteMachine.HEADER_CHUNK);
		Collections.addAll(musicData, ByteMachine.TRACK_CHUNK_HEAD);
		for (int x = 0; x < padding; x++) {
			musicData.add((byte) 0x00);
		}
		
		// Divide length into hex bytes
		switch (padding) {
			case 3:
				musicData.add((byte)(dataSize));
				break;
			case 2:
				int temp0 = dataSize / 256;
				dataSize -= 256;
				musicData.add((byte)temp0);
				musicData.add((byte)dataSize);
				break;
			case 1:
				int temp1 = dataSize / 256 / 256;
				dataSize -= 65536;
				int temp2 = dataSize / 256;
				dataSize -= 256;
				musicData.add((byte)temp1);
				musicData.add((byte)temp2);
				musicData.add((byte)dataSize);
				break;
			case 0:
				int tempA = dataSize / 256 / 256 / 256;
				dataSize -= 16777216;
				int tempB = dataSize / 256 / 256;
				dataSize -= 65536;
				int tempC = dataSize / 256;
				dataSize -= 256;
				musicData.add((byte)tempA);
				musicData.add((byte)tempB);
				musicData.add((byte)tempC);
				musicData.add((byte)dataSize);
				break;
		}
		
		// Add bytes from machine to data
		for (int x = 0; x < bMachine.getByteTape().size(); x++) {
			musicData.add(bMachine.getByteTape().get(x));
		}
		
		// Convert data to something we can output
		byte[] rawData = new byte[musicData.size()];
		for (int i = 0; i < musicData.size(); i++) {
			rawData[i] = musicData.get(i);
		}
		
		// Output file
		File outFile = new File(file);
		if (outFile.exists()) {
			outFile.delete();
		}
		FileOutputStream outStr = new FileOutputStream(outFile);
		
		outStr.write(rawData);
		outStr.close();
		
	}
	
	private static List<GrammarProduction> buildDorianD() {
		List<GrammarProduction> list = new ArrayList<>();

		GrammarProduction aProd = new GrammarProduction("A");
		GrammarProduction cProd = new GrammarProduction("C");
		GrammarProduction dProd = new GrammarProduction("D");
		GrammarProduction eProd = new GrammarProduction("E");
		GrammarProduction fProd = new GrammarProduction("F");
		GrammarProduction gProd = new GrammarProduction("G");
		
		aProd.addProduction("amA", 0.1);
		aProd.addProduction("a-A", 0.1);
		aProd.addProduction("dmD", 0.2);
		aProd.addProduction("d-D", 0.2);
		aProd.addProduction("fMF", 0.2);
		aProd.addProduction("f+F", 0.2);

		cProd.addProduction("dmD", 0.3);
		cProd.addProduction("d-D", 0.3);
		cProd.addProduction("emE", 0.2);
		cProd.addProduction("e-E", 0.2);

		dProd.addProduction("dmD", 0.1);
		dProd.addProduction("d-D", 0.2);
		dProd.addProduction("", 0.2);
		dProd.addProduction("gMG", 0.1);
		dProd.addProduction("g7G", 0.1);
		dProd.addProduction("cMC", 0.1);
		dProd.addProduction("c+C", 0.1);
		dProd.addProduction("fMF", 0.05);
		dProd.addProduction("f+F", 0.05);

		eProd.addProduction("cMC", 0.3);
		eProd.addProduction("c+C", 0.3);
		eProd.addProduction("amA", 0.2);
		eProd.addProduction("a-A", 0.2);

		fProd.addProduction("emE", 0.1);
		fProd.addProduction("e-E", 0.1);
		fProd.addProduction("cMC", 0.2);
		fProd.addProduction("c+C", 0.2);
		fProd.addProduction("amA", 0.2);
		fProd.addProduction("amA", 0.2);

		gProd.addProduction("gMG", 0.1);
		gProd.addProduction("g7G", 0.2);
		gProd.addProduction("dmD", 0.1);
		gProd.addProduction("d-D", 0.1);
		gProd.addProduction("", 0.2);
		gProd.addProduction("cMC", 0.1);
		gProd.addProduction("c+C", 0.1);
		gProd.addProduction("amA", 0.05);
		gProd.addProduction("a-A", 0.05);

		list.add(aProd);
		list.add(cProd);
		list.add(eProd);
		list.add(dProd);
		list.add(fProd);
		list.add(gProd);
		
		return list;
	}
	
}
