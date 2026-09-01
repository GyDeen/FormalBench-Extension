import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindNthDigit {

	public static int findNthDigit(int num, int b, int r) {
		int result = 0;
		while (r > 0) {
			r -= 1;
			num *= 10;
			result = num / b;
			num %= b;
		}
		return result;
	}
}
