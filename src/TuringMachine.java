import java.util.ArrayList;
import java.util.Map;


public class TuringMachine {
	
	public static String BLANK = "_";
	
	private Map<DeltaInput, DeltaOutput> lookupTable;
	private ArrayList<Integer> states;
	private ArrayList<Integer> accepting;
	private ArrayList<String> tape;
	private int headPos;
	private int currentState;
	private boolean ready;
	private boolean halted;
	private DeltaInput nextStep;
	private DeltaOutput stepResult;
	
	// Constructor
	public TuringMachine() {
		resetMachine();
	}
	
	// Resets the machine to be ready to load new data.
	public void resetMachine() {
		lookupTable = null;
		states = null;
		accepting = new ArrayList<Integer>();
		tape = null;
		headPos = 0;
		currentState = 0;
		ready = false;
		halted = true;
		nextStep = new DeltaInput(0, "");
		stepResult = new DeltaOutput(0, "", false);
	}
	
	// Set lookup table
	public void loadTable(Map<DeltaInput, DeltaOutput> table) {
		lookupTable = table;
		checkReady();
	}
	// Set valid states
	public void loadStates(ArrayList<Integer> list) {
		states = list;
		checkReady();
	}
	// Set accepting states
	public void loadAccepting(ArrayList<Integer> list) {
		accepting = list;
	}
	// Load tape into machine
	public void loadTape(ArrayList<String> input) {
		tape = padTape(input);
		checkReady();
	}
	// Pad table with blank symbol on both ends
	public ArrayList<String> padTape(ArrayList<String> toPad) {
		toPad.add(0, BLANK);
		toPad.add(BLANK);
		headPos = 1;
		return toPad;
	}
	// Check if machine has required elements set
	private boolean checkReady() {
		if (lookupTable != null && states != null && tape != null) {
			ready = true;
			halted = false;
			return true;
		}
		return false;
	}
	// Check if machine should be halted
	private boolean checkHalt(DeltaInput next) {
		if (accepting.contains(currentState) || !lookupTable.containsKey(next)) {
			halted = true;
			return true;
		}
		return false;
	}
	
	// Take one step in the machine
	public boolean step() {
		if (!isHalted()) {
			// Extend tape if need be
			if (headPos >= tape.size()) {
				tape.add(BLANK);
			} else if (headPos < 0) {
				tape.add(0, BLANK);
				headPos = 0;
			}
			nextStep.setState(currentState);
			nextStep.setChar(tape.get(headPos));
			if (!checkHalt(nextStep)) {
				stepResult = lookupTable.get(nextStep);
				currentState = stepResult.getState();
				tape.set(headPos, stepResult.getChar());
				if (stepResult.getDirection() == DeltaOutput.RIGHT) {
					headPos++;
				} else {
					headPos--;
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean isReady() {
		return ready;
	}
	public boolean isHalted() {
		return halted;
	}
	
	public String getTape() {
		String out = "";
		for (int n = 0; n < tape.size(); n++) {
			out += tape.get(n);
		}
		return out;
	}
	
	@Override
	public String toString() {
		String temp = "State: " + currentState + " Tape: |";
		for (int n = 0; n < tape.size(); n++) {
			if (n == headPos) {
				temp += ">" + tape.get(n) + "<|";
			} else {
				temp += tape.get(n) + "|";
			}
		}
		return temp;
	}
}
