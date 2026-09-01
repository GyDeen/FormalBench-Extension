import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SameLength {

	public static Boolean sameLength(int a, int b) {
		boolean same = a == 0;
		boolean temp = b == 0;
		if (same || temp) {
			return false;
		}
		int count = 0;
		boolean result = a != 0;
		boolean tmp = b != 0;
		while (result || tmp) {
			int offset = a % 10;
			if (offset == b % 10) {
				count++;
			}
			a = a / 10;
			b = b / 10;
		}
		return count == 1;
	}
}
