import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfSeries {

	public static int sumOfSeries(int n) {
		int sum = 0;
		int max = 1;
		while (max <= n) {
			sum += (max * max * max);
			max++;
		}
		return sum;
	}
}
