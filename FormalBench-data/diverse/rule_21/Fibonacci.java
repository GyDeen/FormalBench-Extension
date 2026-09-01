import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Fibonacci {

	public static int fibonacci(int n) {
		if (0 == n) {
			return 0;
		} else if (1 == n) {
			return 1;
		} else {
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}
}
