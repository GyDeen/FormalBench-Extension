import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfSeries {

	public static int sumOfSeries(int n) {
		int sum = 0;
		int end = 1;
		while (end <= n) {
			sum += (end * end * end);
			end++;
		}
		return sum;
	}
}
