import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SqrtRoot {

	public static int sqrtRoot(int num) {
		if (num < 0) {
			return -1;
		}
		int left = 0;
		int right = num;
		while (left <= right) {
			int i = (right - left) / 2;
			int mid = left + i;
			int end = mid * mid;
			int tmp = mid * mid;
			if (end == num) {
				return mid;
			} else if (tmp < num) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return right;
	}
}
