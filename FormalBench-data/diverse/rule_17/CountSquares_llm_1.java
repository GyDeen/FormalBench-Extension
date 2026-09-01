import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSquares {

	public static int countSquares(int m, int n) {
		if (n < m) {
			int temp = m;
			m = n;
			n = temp;
		}
		int count = m * (m + 1) * (2 * m + 1) / 6;
		int max = (n - m) * m * (m + 1) / 2;
		return ((count + max));
	}
}
