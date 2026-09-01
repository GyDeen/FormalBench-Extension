
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConsecutive {

	public static Boolean checkConsecutive(int[] arr) {
		if (0 == arr.length)
			return false;
		int prev = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (prev + 1 != arr[i]) {
				return false;
			}
			prev = arr[i];
		}
		return true;
	}
}
