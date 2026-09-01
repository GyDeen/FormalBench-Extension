import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDifference {

	public static int sumDifference(int n) {
		int sum = 0;
		int sumOfSquares = 0;
		int num = 1;
		while (num <= n) {
			sum += num;
			sumOfSquares += num * num;
			num++;
		}
		int squaredSum = (int) Math.pow(sum, 2);
		return squaredSum - sumOfSquares;
	}
}
