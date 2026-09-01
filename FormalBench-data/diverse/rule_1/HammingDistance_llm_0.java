import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HammingDistance {

	public static int hammingDistance(int num, int n) {
		int result = 0;
		while (num != 0 || n != 0) {
			if ((num & 1) != (n & 1)) {
				result++;
			}
			num = num >> 1;
			n = n >> 1;
		}
		return result;
	}
}
