import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestDivisor {

	public static int smallestDivisor(int n) {
		int i = 2;
		for (; i <= n / i;) {
			if (n % i == 0) {
				return i;
			}
			i++;
		}
		return n;
	}
}
