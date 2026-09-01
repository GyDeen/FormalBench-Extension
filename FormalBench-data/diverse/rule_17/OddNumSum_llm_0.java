import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddNumSum {

	public static int oddNumSum(int n) {
		int sum = 0;
		int total = n * 2;
		for (int i = 1; i < total; i += 2) {
			sum += ((int) Math.pow(i, 4));
		}
		return sum;
	}
}
