import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDigits {

	public static int sumDigits(int num) {
		int ret = 0;
		while (num > 0) {
			ret += num % 10;
			num /= 10;
		}
		return ret;
	}
}
