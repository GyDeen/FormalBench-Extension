import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Divisor {

	public static int divisor(int n) {
		int count = 0;
		for (int i = 1; i <= n; i++) {
			int total = n % i;
			if (total == 0) {
				count++;
			}
		}
		return count;
	}
}
