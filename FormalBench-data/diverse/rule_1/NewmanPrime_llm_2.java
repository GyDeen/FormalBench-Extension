import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NewmanPrime {

	public static int newmanPrime(int num) {
		if (num == 0 || num == 1) {
			return 1;
		}
		return 2 * newmanPrime(num - 1) + newmanPrime(num - 2);
	}
}
