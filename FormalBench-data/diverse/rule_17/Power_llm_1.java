import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Power {

	public static int power(int a, int b) {
		if (b == 0) {
			return 1;
		}
		int result = b - 1;
		return a * power(a, result);
	}
}
