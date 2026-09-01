import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDigit {

	public static int countDigit(int number) {
		int result = 0;
		while (number > 0) {
			number /= 10;
			result++;
		}
		return result;
	}
}
