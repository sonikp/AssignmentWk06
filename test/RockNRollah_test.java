import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import junit.framework.TestCase;


public class RockNRollah_test extends TestCase {
	
	public static String newline = System.getProperty("line.separator");
	
	public void testDistributionThresholds() {
		final int rolls = 36000000;
		int expectedFrequency[] = {
				(int) ((1/36f) * rolls),    // prob 2
				(int) ((2/36f) * rolls),    // prob 3
				(int) ((3/36f) * rolls),    // prob 4
				(int) ((4/36f) * rolls),    // ...
				(int) ((5/36f) * rolls),
				(int) ((6/36f) * rolls),
				(int) ((5/36f) * rolls),
				(int) ((4/36f) * rolls),
				(int) ((3/36f) * rolls),
				(int) ((2/36f) * rolls),
				(int) ((1/36f) * rolls)
		};
		
		// Capture the output
		final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(capturedOut));
		
		String[] arr = {};
		RockNRollah.main(arr);
		System.err.println(capturedOut);
		
		Scanner sc = new Scanner(capturedOut.toString());
		try {
			/* FIRST LINE
			 */
			assertEquals("The program's output is non-conformant: no output was recorded", true, sc.hasNext());
			
			nextLineContaining(sc, "value");    // Read to and discard the table heading
			int ii;
			for (ii = 0; ii < expectedFrequency.length && sc.hasNext(); ii++) {
				System.err.println("Discarding val: " + sc.next());    // Discard the "Value"
				int curFreq = sc.nextInt();
				System.err.println("Discarding: " + sc.nextLine());    // Discard the EOL whitespace
				assertEquals("WARNING: Extremely unprobable outcome!", expectedFrequency[ii], curFreq, expectedFrequency[ii] * 0.02);    // Allow for a 2% margin of error
			}
			
			if (ii != expectedFrequency.length) {
				fail("The frequency table did not include entries for each possible value and/or was incorrectly formatted.");
			}
			
		} catch (Exception e) {
			assertThrowableTestFailure(e);
		}
	}
	
	/**
	 * @param sc the Scanner to read from
	 * @param str the string to identify within the candidate lines (Scanner's buffer). It is converted to lower case in support of a case-insensitive search.
	 * @return the next line from the scanner containing the specified parameter or null if none are present
	 */
	private String nextLineContaining(Scanner sc, String str) {
		str = str.toLowerCase();
		String currentLine;
		do {
			currentLine = sc.nextLine().toLowerCase();
		} while(!currentLine.contains(str) && sc.hasNextLine());
		if (!currentLine.contains(str)) {
			currentLine = null;
		}
		return currentLine;
	}
	
	/* method name retrieval code courtesy of: http://dev.kanngard.net/Permalinks/ID_20030114224837.html
	 */
	private void assertThrowableTestFailure(Throwable thrown) {
		StackTraceElement stackTraceElements[] =
            (new Throwable()).getStackTrace();
		fail(thrown.getClass().getName() + " encountered! Unable to successfully execute test: " + stackTraceElements[1].toString());
	}
}
