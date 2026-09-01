import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddNumSum {

	public static int oddNumSum(int n) {
		int sum = 0;
		int total = 1;
		while (total < n * 2) {
			sum += ((int) Math.pow(total, 4));
			total += 2;
		}
		return sum;
	}
}
