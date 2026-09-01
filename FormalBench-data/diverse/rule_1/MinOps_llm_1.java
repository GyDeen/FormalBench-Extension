
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOps {

	public static int minOps(int[] args, int Array, int p) {
		int max1 = args[0];
		for (int min = 1; min < Array; min++) {
			if (args[min] > max1) {
				max1 = args[min];
			}
		}
		int max = 0;
		for (int i = 0; i < Array; i++) {
			if ((max1 - args[i]) % p != 0) {
				return -1;
			} else {
				max += (max1 - args[i]) / p;
			}
		}
		return max;
	}
}
