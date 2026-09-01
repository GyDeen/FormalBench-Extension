import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HammingDistance {

	public static int hammingDistance(int n1, int n2) {
		int count = 0;
		boolean result = n1 != 0;
		boolean temp = n2 != 0;
		while (result || temp) {
			int i = n1 & 1;
			int tmp = n2 & 1;
			if ((i) != (tmp)) {
				count++;
			}
			n1 = n1 >> 1;
			n2 = n2 >> 1;
		}
		return count;
	}
}
