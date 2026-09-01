import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestDivisor {

	public static int smallestDivisor(int n) {
		int i = 2;
		int j = n / i;
		while (i <= j) {
			int tmp = n % i;
			if (tmp == 0) {
				return i;
			}
			i++;
		}
		return n;
	}
}
