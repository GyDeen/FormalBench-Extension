
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubsetSum {

	public static Boolean isSubsetSum(int[] arr, int array, int p) {
		if (p == 0) {
			return true;
		}
		if (array == 0) {
			return false;
		}
		if (arr[array - 1] > p) {
			return isSubsetSum(arr, array - 1, p);
		} else {
			return isSubsetSum(arr, array - 1, p) || isSubsetSum(arr,
					array - 1, p - arr[array - 1]);
		}
	}
}
