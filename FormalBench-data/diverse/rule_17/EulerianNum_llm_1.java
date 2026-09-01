import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EulerianNum {

	public static int eulerianNum(int n, int m) {
		boolean b = m >= n;
		boolean tmp = n == 0;
		if (b || tmp) {
			return 0;
		}
		if (m == 0) {
			return 1;
		}
		int result = (n - m) * eulerianNum(n - 1, m - 1);
		int temp = (m + 1) * eulerianNum(n - 1, m);
		return (result + temp);
	}
}
