import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddNumSum {

	public static int oddNumSum(int n) {
		int sum = 0;
		int max = 1;
		while (max < n * 2) {
			sum += ((int) Math.pow(max, 4));
			max += 2;
		}
		return sum;
	}
}
