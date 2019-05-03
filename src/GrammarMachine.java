import java.util.Map;
import java.util.Hashtable;

public class GrammarMachine {

	private boolean clean;
	private String currentString;
	private Map<String, GrammarProduction> productions;
	
	public GrammarMachine() {
		currentString = "";
		productions = new Hashtable<>();
		clean = true;
	}
	
	public void addProduction(GrammarProduction production) {
		productions.put(production.getProducer(), production);
	}
	
	public void loadSeed(String seed) {
		currentString = seed;
		clean = true;
	}
	
	public void runGrammar() {
		
		// Check if initial production is needed
		if (clean) {
			clean = false;
			currentString = productions.get(currentString).nextProduction();
		}
		
		boolean isDone = checkDone();
		
		try {
			while(!isDone) {
				// Only expand one symbol at a time
				if (currentString.contains("A")) {
					retrieveProduction("A");
				} else if (currentString.contains("B")) {
					retrieveProduction("B");
				} else if (currentString.contains("C")) {
					retrieveProduction("C");
				} else if (currentString.contains("D")) {
					retrieveProduction("D");
				} else if (currentString.contains("E")) {
					retrieveProduction("E");
				} else if (currentString.contains("F")) {
					retrieveProduction("F");
				} else if (currentString.contains("G")) {
					retrieveProduction("G");
				}
				
				// Check if any nonterminal symbols remain
				isDone = checkDone();
			}
		} catch (NullPointerException npe) {
			System.err.println("Symbol not found in productions");
			npe.printStackTrace();
		}
		
	}
	
	// Returns true if no nonterminal symbols exist
	private boolean checkDone() {
		return !(currentString.contains("A") || currentString.contains("B") || currentString.contains("C") || currentString.contains("D")
				 || currentString.contains("E") || currentString.contains("F") || currentString.contains("G"));
	}
	
	// Expands first given nonterminal symbol
	private void retrieveProduction(String symbol) throws NullPointerException {
			String temp = productions.get(symbol).nextProduction();
			currentString = currentString.replaceFirst(symbol, temp);
	}
	
	public String getString() {
		return currentString;
	}
	
}
