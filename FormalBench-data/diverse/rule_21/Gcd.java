import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Gcd {

	public static int gcd(int x, int y) {
		if (0 == y) {
			return x;
		} else {
			return gcd(y, x % y);
		}
	}
}
