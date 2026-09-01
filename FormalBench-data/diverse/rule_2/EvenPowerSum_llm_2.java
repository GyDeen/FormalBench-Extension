import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenPowerSum {

	public static int evenPowerSum(int n) {
		int sum = 0;
		int result = 1;
		while (result <= n) {
			int j = 2 * result;
			sum = sum + (j * j * j * j);
			result++;
		}
		return sum;
	}
}
