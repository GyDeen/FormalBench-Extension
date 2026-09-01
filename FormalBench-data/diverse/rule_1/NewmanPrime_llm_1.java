import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NewmanPrime {

	public static int newmanPrime(int size) {
		if (size == 0 || size == 1) {
			return 1;
		}
		return 2 * newmanPrime(size - 1) + newmanPrime(size - 2);
	}
}
