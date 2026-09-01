import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenPowerSum {

	public static int evenPowerSum(int n) {
		int sum = 0;
		for (int i = 1; n >= i; i++) {
			int j = 2 * i;
			sum = sum + (j * j * j * j);
		}
		return sum;
	}
}
