
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreEqual {

	public static Boolean areEqual(int[] arr1, int[] arr2, int n, int m) {
		if (arr1 == arr2) {
			return true;
		}

		if (n != m) {
			return false;
		}

		int sum1 = 0, sum2 = 0;
		int num = 0;
		while (num < n) {
			sum1 += arr1[num];
			sum2 += arr2[num];
			num++;
		}

		return sum1 == sum2;
	}
}
