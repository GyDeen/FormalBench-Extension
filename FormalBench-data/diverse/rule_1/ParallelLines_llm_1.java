
import java.io.*;
import java.lang.*;
import java.math.*;

class ParallelLines {

	public static Boolean parallelLines(int[] lines, int[] line) {
		int x1 = lines[0];
		int y = lines[1];
		int z = line[0];
		int Y = line[1];
		return (x1 * Y == z * y);
	}
}
