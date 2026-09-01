
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMajority {

	public static Boolean isMajority(int[] arr, int n, int x) {
		int count = 0;
		int index = 0;
		while (index < n) {
			if (arr[index] == x) {
				count++;
			}
			index++;
		}
		return count > n / 2;
	}
}
