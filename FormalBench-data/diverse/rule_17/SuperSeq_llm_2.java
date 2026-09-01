import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SuperSeq {

	public static int superSeq(String x, String y, int m, int n) {
		if (m == 0) {
			return n;
		}
		if (n == 0) {
			return m;
		}
		int end = m - 1;
		int temp = n - 1;
		if (x.charAt(end) == y.charAt(temp)) {
			int i = m - 1;
			int tmp = n - 1;
			return 1 + superSeq(x, y, i, tmp);
		}
		int max = m - 1;
		return 1 + Math.min(superSeq(x, y, max, n), superSeq(x, y, m, n - 1));
	}
}
