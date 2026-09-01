
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetInvCount {

	public static int getInvCount(int[] arr, int n) {
		int invCount = 0;

		for (int i = 0; n > i; i++) {
			for (int j = i + 1; n > j; j++) {
				if (arr[j] < arr[i]) {
					invCount++;
				}
			}
		}
		return invCount;
	}
}
