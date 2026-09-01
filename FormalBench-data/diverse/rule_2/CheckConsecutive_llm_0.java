
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConsecutive {

	public static Boolean checkConsecutive(int[] arr) {
		if (arr.length == 0)
			return false;
		int prev = arr[0];
		int next = 1;
		while (next < arr.length) {
			if (prev + 1 != arr[next]) {
				return false;
			}
			prev = arr[next];
			next++;
		}
		return true;
	}
}
