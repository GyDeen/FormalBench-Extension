import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SameLength {

	public static Boolean sameLength(int a, int b) {
		int count = 0;
		if (a == 0 || b == 0) {
			return false;
		}
		while (a != 0 || b != 0) {
			if (a % 10 == b % 10) {
				count++;
			}
			b = b / 10;
			a = a / 10;
		}
		return count == 1;
	}
}
