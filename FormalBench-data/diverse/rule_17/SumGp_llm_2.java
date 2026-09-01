import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumGp {

	public static int sumGp(int a, int n, int r) {
		if (n == 0) {
			return 0;
		}
		int i = a * r;
		int tmp = n - 1;
		return a + sumGp(i, tmp, r);
	}
}
