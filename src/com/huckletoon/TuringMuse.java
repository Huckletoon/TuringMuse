package com.huckletoon;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.huckletoon.grammar.GrammarMachine;
import com.huckletoon.grammar.GrammarProduction;

public class TuringMuse {
	
	static String file = "temp.mid";
	
	public static Byte[] HEADER = {0x4d, 0x54, 0x68, 0x64, 0x00, 0x00, 0x00, 0x06, 0x00, 0x01, 0x00, 0x02, 0x01, (byte) 0xe0};
	
	// Test data used for analysis
	static Byte[] data = new Byte[] {
			0x4d, 0x54, 0x68, 0x64, 0x00, 0x00, 0x00, 0x06, 0x00, 0x01, 0x00, 0x02, 0x01, (byte) 0xe0, 
			0x4d, 0x54, 0x72, 0x6b, 0x00, 0x00, 0x00, 0x27, 
				0x00, (byte) 0xff, 0x03, 0x10, 0x50, 0x49, 0x41, 0x4e, 0x4f, 0x20, 0x54, 0x48, 0x45, 0x4d, 0x45, 0x20, 0x38, 0x39, 0x5f, 0x41,
				0x00, (byte) 0xff, 0x51, 0x03, 0x07, 0x62, 0x1e, 
				0x00, (byte) 0xff, 0x58, 0x04, 0x04, 0x02, 0x18, 0x08, 
				0x00, (byte) 0xff, 0x2f, 0x00, 
			0x4d, 0x54, 0x72, 0x6b, 0x00, 0x00, 0x00, (byte) 0x92, 
				0x00, (byte) 0xff, 0x03, 0x05, 0x50, 0x69, 0x61, 0x6e, 0x6f, 
				0x00, (byte) 0x90, 0x4a, 0x7f, 
					0x00, 0x4f, 0x7f, 
					0x00, 0x53, 0x7f, 
					0x00, 0x5f, 0x7f, 
				(byte) 0x8b, 0x66, 0x4a, 0x00, 0x00,
			0x4f, 0x00, 0x00, 0x53, 0x00, (byte) 0x81, 0x2a, 0x45, 0x61, 0x00, 0x49, 0x61, 0x00, 0x4c, 0x61, 0x00,
			0x51, 0x61, (byte) 0x8f, 0x2f, 0x45, 0x00, 0x00, 0x49, 0x00, 0x00, 0x4c, 0x00, 0x00, 0x51, 0x00, (byte) 0x81,
			0x41, 0x2d, 0x7f, 0x00, 0x39, 0x7f, 0x00, 0x4a, 0x7f, 0x00, 0x4c, 0x7f, 0x00, 0x53, 0x7f, (byte) 0x8c,
			0x33, 0x2d, 0x00, 0x00, 0x39, 0x00, 0x00, 0x4a, 0x00, 0x00, 0x4c, 0x00, 0x00, 0x53, 0x00, 0x5d,
			0x45, 0x60, 0x00, 0x49, 0x60, 0x00, 0x4c, 0x60, 0x60, 0x51, 0x60, (byte) 0x8e, 0x03, 0x5f, 0x00, 0x22,
			0x45, 0x00, 0x00, 0x49, 0x00, 0x00, 0x4c, 0x00, 0x00, 0x51, 0x00, 0x5b, 0x45, 0x60, 0x00, 0x49,
			0x60, 0x00, 0x4c, 0x60, 0x00, 0x51, 0x61, 0x54, 0x45, 0x00, 0x00, 0x49, 0x00, 0x00, 0x4c, 0x00,
			0x00, 0x51, 0x00, 0x00, (byte) 0xff, 0x2f, 0x00
	};
	
	public static void main(String[] args) throws IOException {
		
		
		GrammarMachine gm = new GrammarMachine();
		List<GrammarProduction> prods = buildDorianD();
		for (int x = 0; x < prods.size(); x++) {
			gm.addProduction(prods.get(x));
		}
		
		gm.loadSeed("A");
		gm.runGrammar();
		String chordProgression = gm.getString();
		System.out.println(chordProgression);
		
		/*
		 * TODO
		 * Below is pattern to write to midi file
		List<Byte> musicData = new ArrayList<>();
		Collections.addAll(musicData, data);
		
		byte[] testData = new byte[musicData.size()];
		for (int i = 0; i < musicData.size(); i++) {
			testData[i] = musicData.get(i);
		}

		FileOutputStream outStr = new FileOutputStream(file);
		outStr.write(testData);
		outStr.close();
		*/
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
