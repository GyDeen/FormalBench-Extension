import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDigits {

	public static int sumDigits(int base) {
		int result = 0;
		while (base > 0) {
			result += base % 10;
			base /= 10;
		}
		return result;
	}
}
