
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConsecutive {

	public static Boolean checkConsecutive(int[] arr) {
		if (arr.length == 0)
			return false;
		int prev = arr[0];
		for (int i = 1; arr.length > i; i++) {
			if (arr[i] != prev + 1) {
				return false;
			}
			prev = arr[i];
		}
		return true;
	}
}
