
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOdd {

	public static int countOdd(int[] arr) {
		int result = 0;
		for (int i : arr) {
			if (i % 2 != 0) {
				result++;
			}
		}
		return result;
	}
}
