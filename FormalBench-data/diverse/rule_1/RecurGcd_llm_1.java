import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RecurGcd {

	public static int recurGcd(int num, int c) {
		if (num == 0) {
			return c;
		}
		if (c == 0) {
			return num;
		}
		if (num == c) {
			return num;
		}
		return recurGcd(c, num % c);
	}
}
