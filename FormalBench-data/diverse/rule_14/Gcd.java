import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Gcd {

	public static int gcd(int x, int y) {
		if (!(y == 0)) {
			return gcd(y, x % y);
		} else {
			return x;
		}
	}
}
