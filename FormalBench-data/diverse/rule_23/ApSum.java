import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ApSum {

	public static int apSum(int a, int n, int d) {
		int sum = 0;
		for (int i = 0; n > i; i++) {
			sum += (a + i * d);
		}
		return sum;
	}
}
