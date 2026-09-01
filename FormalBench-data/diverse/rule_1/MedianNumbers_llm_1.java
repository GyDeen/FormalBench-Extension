import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MedianNumbers {

	public static Double medianNumbers(int num, int d, int n) {
		double[] median = { num, d, n };
		Arrays.sort(median);
		return median[median.length / 2];
	}
}
