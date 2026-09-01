import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumGp {

	public static int sumGp(int a, int n, int r) {
		if (n == 0) {
			return 0;
		}
		int b = a * r;
		int temp = n - 1;
		return a + sumGp(b, temp, r);
	}
}
