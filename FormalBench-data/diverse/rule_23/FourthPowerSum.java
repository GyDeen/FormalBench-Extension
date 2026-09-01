import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FourthPowerSum {

	public static int fourthPowerSum(int n) {
		int sum = 0;
		for (int i = 1; n >= i; i++) {
			sum += (int) Math.pow(i, 4);
		}
		return sum;
	}
}
