import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RecurGcd {

	public static int recurGcd(int n, int c) {
		if (n == 0) {
			return c;
		}
		if (c == 0) {
			return n;
		}
		if (n == c) {
			return n;
		}
		return recurGcd(c, n % c);
	}
}
