import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSquares {

	public static int countSquares(int m, int n) {
		if (m > n) {
			int temp = m;
			m = n;
			n = temp;
		}
		return ((m * (m + 1) * (2 * m + 1) / 6 + (n - m) * m * (m + 1) / 2));
	}
}
