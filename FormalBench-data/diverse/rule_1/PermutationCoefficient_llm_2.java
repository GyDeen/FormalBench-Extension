import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PermutationCoefficient {

	public static int permutationCoefficient(int seed, int m) {
		int result = 1;
		int ret = 1;
		while (ret <= m) {
			result *= (seed - ret + 1);
			ret++;
		}
		return result;
	}
}
