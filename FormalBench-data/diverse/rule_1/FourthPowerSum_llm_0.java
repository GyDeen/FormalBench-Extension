import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FourthPowerSum {

	public static int fourthPowerSum(int number) {
		int N = 0;
		for (int j = 1; j <= number; j++) {
			N += (int) Math.pow(j, 4);
		}
		return N;
	}
}
