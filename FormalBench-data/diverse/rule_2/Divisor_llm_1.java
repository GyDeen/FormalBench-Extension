import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Divisor {

	public static int divisor(int n) {
		int count = 0;
		int div = 1;
		while (div <= n) {
			if (n % div == 0) {
				count++;
			}
			div++;
		}
		return count;
	}
}
