
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConsecutive {

	public static Boolean checkConsecutive(int[] arr) {
		if (arr.length == 0)
			return false;
		int prev = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int last = prev + 1;
			if (last != arr[i]) {
				return false;
			}
			prev = arr[i];
		}
		return true;
	}
}
