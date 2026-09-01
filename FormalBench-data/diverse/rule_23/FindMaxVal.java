import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxVal {

	public static int findMaxVal(int n, int x, int y) {
		int max = 0;
		for (int i = 1; n >= i; i++) {
			if ((i % x) == y) {
				max = i;
			}
		}
		return max;
	}
}
