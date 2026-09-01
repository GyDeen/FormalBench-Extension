import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Power {

	public static int power(int number, int n) {
		if (n == 0) {
			return 1;
		}
		return number * power(number, n - 1);
	}
}
