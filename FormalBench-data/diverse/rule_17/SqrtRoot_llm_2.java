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
			int tmp = mid * mid;
			int last = mid * mid;
			if (tmp == num) {
				return mid;
			} else if (last < num) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return right;
	}
}
