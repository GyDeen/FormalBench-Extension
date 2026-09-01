
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubsetSum {

	public static Boolean isSubsetSum(int[] set, int n, int sum) {
		if (n == 0) {
			return false;
		}
		if (sum == 0) {
			return true;
		}
		if (set[n - 1] > sum) {
			return isSubsetSum(set, n - 1, sum);
		} else {
			return isSubsetSum(set, n - 1, sum) || isSubsetSum(set, n - 1, sum - set[n - 1]);
		}
	}
}
