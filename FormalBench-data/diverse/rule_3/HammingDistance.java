import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HammingDistance {

	public static int hammingDistance(int n1, int n2) {
		int count = 0;
		for (; n1 != 0 || n2 != 0;) {
			if ((n1 & 1) != (n2 & 1)) {
				count++;
			}
			n1 = n1 >> 1;
			n2 = n2 >> 1;
		}
		return count;
	}
}
