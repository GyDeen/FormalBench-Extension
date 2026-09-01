import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NewmanPrime {

	public static int newmanPrime(int seed) {
		if (seed == 0 || seed == 1) {
			return 1;
		}
		return 2 * newmanPrime(seed - 1) + newmanPrime(seed - 2);
	}
}
