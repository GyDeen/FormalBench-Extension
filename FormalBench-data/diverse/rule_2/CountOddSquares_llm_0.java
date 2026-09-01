import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOddSquares {

	public static int countOddSquares(int n, int m) {
		int count = 0;
		int total = n;
		while (total <= m) {
			double square = Math.sqrt(total);
			if (square % 1 == 0 && total % 1 == 0) {
				count++;
			}
			total++;
		}
		return count;
	}
}
