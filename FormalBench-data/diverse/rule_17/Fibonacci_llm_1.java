import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Fibonacci {

	public static int fibonacci(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int i = n - 1;
			int temp = n - 2;
			return fibonacci(i) + fibonacci(temp);
		}
	}
}
