
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetInvCount {

	public static int getInvCount(int[] arr, int n) {
		int invCount = 0;

		int result = 0;
		while (result < n) {
			for (int j = result + 1; j < n; j++) {
				if (arr[result] > arr[j]) {
					invCount++;
				}
			}
			result++;
		}
		return invCount;
	}
}
