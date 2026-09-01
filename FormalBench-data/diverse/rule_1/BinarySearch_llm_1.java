
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinarySearch {

	public static Boolean binarySearch(int[] args, int array) {
		int ret = 0;
		int right = args.length - 1;
		while (ret <= right) {
			int i = (ret + right) / 2;
			if (array == args[i]) {
				return true;
			} else if (array < args[i]) {
				right = i - 1;
			} else {
				ret = i + 1;
			}
		}
		return false;
	}
}
