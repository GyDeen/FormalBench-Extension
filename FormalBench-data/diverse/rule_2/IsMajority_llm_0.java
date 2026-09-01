
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMajority {

	public static Boolean isMajority(int[] arr, int n, int x) {
		int count = 0;
		int total = 0;
		while (total < n) {
			if (arr[total] == x) {
				count++;
			}
			total++;
		}
		return count > n / 2;
	}
}
