import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Divisor {

	public static int divisor(int n) {
		int count = 0;
		int result = 1;
		while (result <= n) {
			if (n % result == 0) {
				count++;
			}
			result++;
		}
		return count;
	}
}
