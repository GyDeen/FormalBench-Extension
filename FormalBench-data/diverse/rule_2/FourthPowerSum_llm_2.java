import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FourthPowerSum {

	public static int fourthPowerSum(int n) {
		int sum = 0;
		int total = 1;
		while (total <= n) {
			sum += (int) Math.pow(total, 4);
			total++;
		}
		return sum;
	}
}
