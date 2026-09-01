import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HammingDistance {

	public static int hammingDistance(int n1, int n2) {
		int count = 0;
		while (0 != n1 || 0 != n2) {
			if ((n2 & 1) != (n1 & 1)) {
				count++;
			}
			n1 = n1 >> 1;
			n2 = n2 >> 1;
		}
		return count;
	}
}
