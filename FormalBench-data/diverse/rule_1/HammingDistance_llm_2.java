import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HammingDistance {

	public static int hammingDistance(int size, int n) {
		int result = 0;
		while (size != 0 || n != 0) {
			if ((size & 1) != (n & 1)) {
				result++;
			}
			size = size >> 1;
			n = n >> 1;
		}
		return result;
	}
}
