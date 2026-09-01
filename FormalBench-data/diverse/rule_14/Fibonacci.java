import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Fibonacci {

	public static int fibonacci(int n) {
		if (!(n == 0)) {
			if (n == 1) {
				return 1;
			} else {
				return fibonacci(n - 1) + fibonacci(n - 2);
			}
		} else {
			return 0;
		}
	}
}
