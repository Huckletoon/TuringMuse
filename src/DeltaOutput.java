
public class DeltaOutput {
	
	public static boolean RIGHT = true;
	public static boolean LEFT = false;
	
	private int state;
	private String character;
	private boolean direction;
	
	public DeltaOutput (int st, String ch, boolean dir) {
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
	
	public String getChar() {
		return character;
	}
	
	public void setChar(String ch) {
		character = ch;
	}
	
	public boolean getDirection() {
		return direction;
	}
	
	public void setDirection(boolean dir) {
		direction = dir;
	}
}
