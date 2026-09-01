import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MedianNumbers {

	public static Double medianNumbers(int num, int n, int d) {
		double[] median = { num, n, d };
		Arrays.sort(median);
		return median[median.length / 2];
	}
}
