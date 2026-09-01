import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MedianNumbers {

	public static Double medianNumbers(int num, int n, int C) {
		double[] median = { num, n, C };
		Arrays.sort(median);
		return median[median.length / 2];
	}
}
