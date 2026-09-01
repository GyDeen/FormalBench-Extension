import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Divisor {

	public static int divisor(int n) {
		int count = 0;
		for (int i = 1; n >= i; i++) {
			if (n % i == 0) {
				count++;
			}
		}
		return count;
	}
}
