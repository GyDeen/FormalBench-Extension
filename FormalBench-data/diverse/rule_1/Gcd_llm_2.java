import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Gcd {

	public static int gcd(int arg, int b) {
		if (b == 0) {
			return arg;
		} else {
			return gcd(b, arg % b);
		}
	}
}
