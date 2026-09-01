
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetInvCount {

	public static int getInvCount(int[] arr, int n) {
		int invCount = 0;

		int count = 0;
		while (count < n) {
			for (int j = count + 1; j < n; j++) {
				if (arr[count] > arr[j]) {
					invCount++;
				}
			}
			count++;
		}
		return invCount;
	}
}
