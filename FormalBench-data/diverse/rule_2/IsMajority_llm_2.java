
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMajority {

	public static Boolean isMajority(int[] arr, int n, int x) {
		int count = 0;
		int sum = 0;
		while (sum < n) {
			if (arr[sum] == x) {
				count++;
			}
			sum++;
		}
		return count > n / 2;
	}
}
