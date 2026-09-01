import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FifthPowerSum {

	public static int fifthPowerSum(int n) {
		int sum = 0;
		int result = 1;
		while (result <= n) {
			sum += (int) Math.pow(result, 5);
			result++;
		}
		return sum;
	}
}
