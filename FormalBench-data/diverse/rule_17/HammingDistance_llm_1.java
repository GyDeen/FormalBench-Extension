import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HammingDistance {

	public static int hammingDistance(int n1, int n2) {
		int count = 0;
		boolean result = n1 != 0;
		boolean tmp = n2 != 0;
		while (result || tmp) {
			int i = n1 & 1;
			int n = n2 & 1;
			if ((i) != (n)) {
				count++;
			}
			n1 = n1 >> 1;
			n2 = n2 >> 1;
		}
		return count;
	}
}
