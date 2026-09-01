import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDifference {

	public static int sumDifference(int n) {
		int sum = 0;
		int sumOfSquares = 0;
		for (int i = 1; n >= i; i++) {
			sum += i;
			sumOfSquares += i * i;
		}
		int squaredSum = (int) Math.pow(sum, 2);
		return squaredSum - sumOfSquares;
	}
}
