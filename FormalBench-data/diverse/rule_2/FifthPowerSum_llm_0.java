import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FifthPowerSum {

	public static int fifthPowerSum(int n) {
		int sum = 0;
		int total = 1;
		while (total <= n) {
			sum += (int) Math.pow(total, 5);
			total++;
		}
		return sum;
	}
}
