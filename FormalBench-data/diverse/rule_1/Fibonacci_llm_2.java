import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Fibonacci {

	public static int fibonacci(int b) {
		if (b == 0) {
			return 0;
		} else if (b == 1) {
			return 1;
		} else {
			return fibonacci(b - 1) + fibonacci(b - 2);
		}
	}
}
