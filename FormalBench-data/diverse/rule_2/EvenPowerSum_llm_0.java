import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenPowerSum {

	public static int evenPowerSum(int n) {
		int sum = 0;
		int total = 1;
		while (total <= n) {
			int j = 2 * total;
			sum = sum + (j * j * j * j);
			total++;
		}
		return sum;
	}
}
