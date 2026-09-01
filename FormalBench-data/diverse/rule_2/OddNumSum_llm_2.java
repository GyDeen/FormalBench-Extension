import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddNumSum {

	public static int oddNumSum(int n) {
		int sum = 0;
		int result = 1;
		while (result < n * 2) {
			sum += ((int) Math.pow(result, 4));
			result += 2;
		}
		return sum;
	}
}
