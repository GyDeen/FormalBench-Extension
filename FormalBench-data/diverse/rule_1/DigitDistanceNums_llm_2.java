import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DigitDistanceNums {

	public static int digitDistanceNums(int num, int c) {
		int result = 0;
		int i = Math.abs(num - c);
		while (i > 0) {
			result += i % 10;
			i /= 10;
		}
		return result;
	}
}
