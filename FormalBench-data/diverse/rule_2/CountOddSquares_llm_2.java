import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOddSquares {

	public static int countOddSquares(int n, int m) {
		int count = 0;
		int max = n;
		while (max <= m) {
			double square = Math.sqrt(max);
			if (square % 1 == 0 && max % 1 == 0) {
				count++;
			}
			max++;
		}
		return count;
	}
}
