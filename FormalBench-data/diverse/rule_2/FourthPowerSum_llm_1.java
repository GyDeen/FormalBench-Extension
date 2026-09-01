import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FourthPowerSum {

	public static int fourthPowerSum(int n) {
		int sum = 0;
		int end = 1;
		while (end <= n) {
			sum += (int) Math.pow(end, 4);
			end++;
		}
		return sum;
	}
}
