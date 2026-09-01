import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenPowerSum {

	public static int evenPowerSum(int n) {
		int sum = 0;
		int max = 1;
		while (max <= n) {
			int j = 2 * max;
			sum = sum + (j * j * j * j);
			max++;
		}
		return sum;
	}
}
