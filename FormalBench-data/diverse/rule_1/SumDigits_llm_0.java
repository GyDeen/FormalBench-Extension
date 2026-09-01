import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDigits {

	public static int sumDigits(int num) {
		int result = 0;
		while (num > 0) {
			result += num % 10;
			num /= 10;
		}
		return result;
	}
}
