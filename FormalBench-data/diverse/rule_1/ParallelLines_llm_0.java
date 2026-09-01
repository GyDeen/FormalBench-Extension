
import java.io.*;
import java.lang.*;
import java.math.*;

class ParallelLines {

	public static Boolean parallelLines(int[] lines, int[] line) {
		int x1 = lines[0];
		int x = lines[1];
		int z = line[0];
		int y = line[1];
		return (x1 * y == z * x);
	}
}
