
import java.io.*;
import java.lang.*;
import java.math.*;

class ParallelLines {

	public static Boolean parallelLines(int[] arr, int[] line) {
		int x1 = arr[0];
		int x = arr[1];
		int z = line[0];
		int y = line[1];
		return (x1 * y == z * x);
	}
}
