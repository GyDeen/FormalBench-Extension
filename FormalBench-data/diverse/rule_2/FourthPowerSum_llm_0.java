import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FourthPowerSum {

	public static int fourthPowerSum(int n) {
		int sum = 0;
		int num = 1;
		while (num <= n) {
			sum += (int) Math.pow(num, 4);
			num++;
		}
		return sum;
	}
}
