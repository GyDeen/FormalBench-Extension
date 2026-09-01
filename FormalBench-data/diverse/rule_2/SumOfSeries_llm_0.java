import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfSeries {

	public static int sumOfSeries(int n) {
		int sum = 0;
		int total = 1;
		while (total <= n) {
			sum += (total * total * total);
			total++;
		}
		return sum;
	}
}
