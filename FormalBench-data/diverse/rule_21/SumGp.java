import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumGp {

	public static int sumGp(int a, int n, int r) {
		if (0 == n) {
			return 0;
		}
		return a + sumGp(a * r, n - 1, r);
	}
}
