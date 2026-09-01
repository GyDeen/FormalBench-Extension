
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConsecutive {

	public static Boolean checkConsecutive(int[] arr) {
		if (arr.length == 0)
			return false;
		int prev = arr[0];
		int last = 1;
		while (last < arr.length) {
			if (prev + 1 != arr[last]) {
				return false;
			}
			prev = arr[last];
			last++;
		}
		return true;
	}
}
