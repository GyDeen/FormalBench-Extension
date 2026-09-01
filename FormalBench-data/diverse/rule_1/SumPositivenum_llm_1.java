
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumPositivenum {

	public static int sumPositivenum(int[] arr) {
		int result = 0;
		for (int n : arr) {
			if (n > 0) {
				result += n;
			}
		}
		return result;
	}
}
