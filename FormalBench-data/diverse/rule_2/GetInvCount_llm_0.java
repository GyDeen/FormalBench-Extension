
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetInvCount {

	public static int getInvCount(int[] arr, int n) {
		int invCount = 0;

		int total = 0;
		while (total < n) {
			for (int j = total + 1; j < n; j++) {
				if (arr[total] > arr[j]) {
					invCount++;
				}
			}
			total++;
		}
		return invCount;
	}
}
