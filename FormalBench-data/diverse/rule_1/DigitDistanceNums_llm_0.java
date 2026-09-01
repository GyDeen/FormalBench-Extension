import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DigitDistanceNums {

	public static int digitDistanceNums(int num, int c) {
		int result = 0;
		int r = Math.abs(num - c);
		while (r > 0) {
			result += r % 10;
			r /= 10;
		}
		return result;
	}
}
