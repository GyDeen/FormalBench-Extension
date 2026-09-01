import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FourthPowerSum {

	public static int fourthPowerSum(int number) {
		int N = 0;
		for (int c = 1; c <= number; c++) {
			N += (int) Math.pow(c, 4);
		}
		return N;
	}
}
