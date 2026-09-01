
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConsecutive {

	public static Boolean checkConsecutive(int[] arr) {
		if (arr.length == 0)
			return false;
		int prev = arr[0];
		int cur = 1;
		while (cur < arr.length) {
			if (prev + 1 != arr[cur]) {
				return false;
			}
			prev = arr[cur];
			cur++;
		}
		return true;
	}
}
