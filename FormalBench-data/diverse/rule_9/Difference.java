import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Difference {

	public static int difference(int n) {
		int sum = 0;
		int sum1 = 0;
		for (int i = 1; i <= n; i++) {
			sum = sum + (i * i * i);
		}
		for (int i = 1; i <= n; i++) {
			sum1 = sum1 + i;
		}
		return sum - sum1;
	}
}
