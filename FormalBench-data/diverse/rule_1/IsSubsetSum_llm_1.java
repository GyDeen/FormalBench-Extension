
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubsetSum {

	public static Boolean isSubsetSum(int[] arr, int Array, int k) {
		if (k == 0) {
			return true;
		}
		if (Array == 0) {
			return false;
		}
		if (arr[Array - 1] > k) {
			return isSubsetSum(arr, Array - 1, k);
		} else {
			return isSubsetSum(arr, Array - 1, k) || isSubsetSum(arr,
					Array - 1, k - arr[Array - 1]);
		}
	}
}
