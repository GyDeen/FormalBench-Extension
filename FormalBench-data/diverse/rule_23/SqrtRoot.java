import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SqrtRoot {

	public static int sqrtRoot(int num) {
		if (0 > num) {
			return -1;
		}
		int left = 0;
		int right = num;
		while (right >= left) {
			int mid = left + (right - left) / 2;
			if (mid * mid == num) {
				return mid;
			} else if (num > mid * mid) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return right;
	}
}
