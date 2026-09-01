import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SameLength {

	public static Boolean sameLength(int a, int b) {
		boolean same = a == 0;
		boolean tmp = b == 0;
		if (same || tmp) {
			return false;
		}
		int count = 0;
		boolean result = a != 0;
		boolean last = b != 0;
		while (result || last) {
			int i = a % 10;
			if (i == b % 10) {
				count++;
			}
			a = a / 10;
			b = b / 10;
		}
		return count == 1;
	}
}
