import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FifthPowerSum {

	public static int fifthPowerSum(int n) {
		int sum = 0;
		int end = 1;
		while (end <= n) {
			sum += (int) Math.pow(end, 5);
			end++;
		}
		return sum;
	}
}
