import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PermutationCoefficient {

	public static int permutationCoefficient(int seed, int p) {
		int result = 1;
		int r = 1;
		while (r <= p) {
			result *= (seed - r + 1);
			r++;
		}
		return result;
	}
}
