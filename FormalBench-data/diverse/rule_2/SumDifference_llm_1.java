import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDifference {

	public static int sumDifference(int n) {
		int sum = 0;
		int sumOfSquares = 0;
		int total = 1;
		while (total <= n) {
			sum += total;
			sumOfSquares += total * total;
			total++;
		}
		int squaredSum = (int) Math.pow(sum, 2);
		return squaredSum - sumOfSquares;
	}
}
