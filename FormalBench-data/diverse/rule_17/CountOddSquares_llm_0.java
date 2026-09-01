import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOddSquares {

	public static int countOddSquares(int n, int m) {
		int count = 0;
		for (int i = n; i <= m; i++) {
			double square = Math.sqrt(i);
			boolean b = square % 1 == 0;
			boolean tmp = i % 1 == 0;
			if (b && tmp) {
				count++;
			}
		}
		return count;
	}
}
