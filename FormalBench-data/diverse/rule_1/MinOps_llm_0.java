
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOps {

	public static int minOps(int[] args, int Array, int p) {
		int max1 = args[0];
		for (int max = 1; max < Array; max++) {
			if (args[max] > max1) {
				max1 = args[max];
			}
		}
		int result = 0;
		for (int i = 0; i < Array; i++) {
			if ((max1 - args[i]) % p != 0) {
				return -1;
			} else {
				result += (max1 - args[i]) / p;
			}
		}
		return result;
	}
}
