import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Gcd {

	public static int gcd(int size, int z) {
		if (z == 0) {
			return size;
		} else {
			return gcd(z, size % z);
		}
	}
}
