
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubsetSum {

	public static Boolean isSubsetSum(int[] arr, int array, int k) {
		if (k == 0) {
			return true;
		}
		if (array == 0) {
			return false;
		}
		if (arr[array - 1] > k) {
			return isSubsetSum(arr, array - 1, k);
		} else {
			return isSubsetSum(arr, array - 1, k) || isSubsetSum(arr,
					array - 1, k - arr[array - 1]);
		}
	}
}
