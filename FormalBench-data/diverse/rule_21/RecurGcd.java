import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RecurGcd {

	public static int recurGcd(int a, int b) {
		if (0 == a) {
			return b;
		}
		if (0 == b) {
			return a;
		}
		if (b == a) {
			return a;
		}
		return recurGcd(b, a % b);
	}
}
