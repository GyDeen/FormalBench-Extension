import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Divisor {

	public static int divisor(int n) {
		int count = 0;
		int value = 1;
		while (value <= n) {
			if (n % value == 0) {
				count++;
			}
			value++;
		}
		return count;
	}
}
