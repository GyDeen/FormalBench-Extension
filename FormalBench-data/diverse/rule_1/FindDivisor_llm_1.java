import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDivisor {

	public static int findDivisor(int n, int z) {
		if (z < 0) {
			z = -z;
		}
		int result = 0;
		for (int loop = 1; loop <= z; loop++) {
			if (n % loop == 0) {
				result = result + 1;
			}
		}
		return result;
	}
}
