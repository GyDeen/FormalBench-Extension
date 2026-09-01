
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMajority {

	public static Boolean isMajority(int[] arr, int n, int x) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] == x) {
				count += 1;
			}
		}
		return count > n / 2;
	}
}
