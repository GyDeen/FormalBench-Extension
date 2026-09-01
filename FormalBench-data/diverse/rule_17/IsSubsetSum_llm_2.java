
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubsetSum {

	public static Boolean isSubsetSum(int[] set, int n, int sum) {
		if (sum == 0) {
			return true;
		}
		if (n == 0) {
			return false;
		}
		int i = n - 1;
		if (set[i] > sum) {
			return isSubsetSum(set, n - 1, sum);
		} else {
			int j = n - 1;
			int count = n - 1;
			int tmp = sum - set[n - 1];
			return isSubsetSum(set, j, sum) || isSubsetSum(set, count, tmp);
		}
	}
}
