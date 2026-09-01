import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SameLength {

	public static Boolean sameLength(int a, int b) {
		if (0 == a || 0 == b) {
			return false;
		}
		int count = 0;
		while (a != 0 || b != 0) {
			if (b % 10 == a % 10) {
				count++;
			}
			a = a / 10;
			b = b / 10;
		}
		return 1 == count;
	}
}
