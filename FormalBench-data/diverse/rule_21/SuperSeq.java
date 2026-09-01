import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SuperSeq {

	public static int superSeq(String x, String y, int m, int n) {
		if (0 == m) {
			return n;
		}
		if (0 == n) {
			return m;
		}
		if (y.charAt(n - 1) == x.charAt(m - 1)) {
			return 1 + superSeq(x, y, m - 1, n - 1);
		}
		return 1 + Math.min(superSeq(x, y, m - 1, n), superSeq(x, y, m, n - 1));
	}
}
