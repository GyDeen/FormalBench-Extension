import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Zigzag {

	public static int zigzag(int n, int k) {
		boolean result = n == 0;
		boolean temp = k == 0;
		if (result && temp) {
			return 1;
		}
		if (k == 0) {
			return 0;
		}
		int end = k - 1;
		int tmp = n - 1;
		int r = n - k;
		return zigzag(n, end) + zigzag(tmp, r);
	}
}
