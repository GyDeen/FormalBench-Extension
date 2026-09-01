import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenPowerSum {

	public static int evenPowerSum(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			int j = 2 * i;
			int tmp = j * j * j * j;
			sum = sum + (tmp);
		}
		return sum;
	}
}
