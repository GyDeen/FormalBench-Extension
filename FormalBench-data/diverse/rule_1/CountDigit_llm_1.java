import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDigit {

	public static int countDigit(int value) {
		int result = 0;
		while (value > 0) {
			value /= 10;
			result++;
		}
		return result;
	}
}
