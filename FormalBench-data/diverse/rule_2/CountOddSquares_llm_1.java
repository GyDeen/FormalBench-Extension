import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOddSquares {

	public static int countOddSquares(int n, int m) {
		int count = 0;
		int result = n;
		while (result <= m) {
			double square = Math.sqrt(result);
			if (square % 1 == 0 && result % 1 == 0) {
				count++;
			}
			result++;
		}
		return count;
	}
}
