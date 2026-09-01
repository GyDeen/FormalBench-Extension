import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestDivisor {

	public static int smallestDivisor(int n) {
		int i = 2;
		while (i <= n / i) {
			if (0 == n % i) {
				return i;
			}
			i++;
		}
		return n;
	}
}
