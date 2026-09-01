import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEven {

	public static int sumEven(int l, int r) {
		int sum = 0;
		int n = l;
		while (n <= r) {
			if (n % 2 == 0) {
				sum += n;
			}
			n++;
		}
		return sum;
	}
}
