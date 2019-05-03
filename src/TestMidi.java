import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TestMidi {
	
	private static String HeaderChunkID = new String(new byte[] {0x4d, 0x54, 0x68, 0x64});
	private static String Length6 = new String(new byte[] {0x00, 0x00, 0x06});
	private static String HeaderFormat1 = new String(new byte[] {0x00, 0x01});
	private static String TimeDivision = new String(new byte[] {0x01, 0x60});
	private static String TrackChunk = new String(new byte[] {0x4d, 0x54, 0x72, 0x6b});
	private static String EndOfTrack = new String(new byte[] {(byte) 0xff, 0x2f, 0x00});
	
	public static void main(String[] args) throws FileNotFoundException {
		
		String file = "test.mid";
		PrintWriter output = new PrintWriter(file);
		
		String test = "";
		test += HeaderChunkID;
		test += Length6;
		test += HeaderFormat1;
		test += new String(new byte[] {0x00, 0x01});
		test += TimeDivision;
		
		String chunk = "";
		chunk += TrackChunk;
		chunk += new String(new byte[] {0x04});
		chunk += new String(new byte[] {0x00});
		chunk += EndOfTrack;

		output.flush();
		output.write(test+chunk);
		output.flush();
		output.close();
		System.out.println(test);
	}

}
