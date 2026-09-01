import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDifference {

	public static int sumDifference(int n) {
		int sum = 0;
		int sumOfSquares = 0;
		int average = 1;
		while (average <= n) {
			sum += average;
			sumOfSquares += average * average;
			average++;
		}
		int squaredSum = (int) Math.pow(sum, 2);
		return squaredSum - sumOfSquares;
	}
}
