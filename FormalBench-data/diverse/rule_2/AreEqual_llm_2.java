
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
		int count = 0;
		while (count < n) {
			sum1 += arr1[count];
			sum2 += arr2[count];
			count++;
		}

		return sum1 == sum2;
	}
}
