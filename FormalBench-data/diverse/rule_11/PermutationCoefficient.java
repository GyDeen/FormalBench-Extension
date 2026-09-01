import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PermutationCoefficient {

	public static int permutationCoefficient(int n, int k) {
		int res = 1, i = 1;
		while (i <= k) {
			res *= (n - i + 1);
			i++;
		}
		return res;
	}
}
