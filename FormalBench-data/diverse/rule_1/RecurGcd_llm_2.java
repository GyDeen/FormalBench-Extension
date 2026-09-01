import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RecurGcd {

	public static int recurGcd(int size, int c) {
		if (size == 0) {
			return c;
		}
		if (c == 0) {
			return size;
		}
		if (size == c) {
			return size;
		}
		return recurGcd(c, size % c);
	}
}
