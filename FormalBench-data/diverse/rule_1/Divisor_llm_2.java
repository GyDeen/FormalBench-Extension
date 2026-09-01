import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Divisor {

	public static int divisor(int num) {
		int div = 0;
		for (int c = 1; c <= num; c++) {
			if (num % c == 0) {
				div++;
			}
		}
		return div;
	}
}
