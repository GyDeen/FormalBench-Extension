
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubsetSum {

	public static Boolean isSubsetSum(int[] set, int n, int sum) {
		if (0 == sum) {
			return true;
		}
		if (0 == n) {
			return false;
		}
		if (set[n - 1] > sum) {
			return isSubsetSum(set, n - 1, sum);
		} else {
			return isSubsetSum(set, n - 1, sum) || isSubsetSum(set, n - 1, sum - set[n - 1]);
		}
	}
}
