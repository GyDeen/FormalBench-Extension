
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxProduct {

	public static int maxProduct(int[] args, int Array) {
		int[] mpis = new int[Array];
		for (int array = 0; array < Array; array++) {
			mpis[array] = args[array];
		}
		for (int i = 1; i < Array; i++) {
			for (int p = 0; p < i; p++) {
				if (args[i] > args[p]
						&& mpis[i] < (mpis[p] * args[i])) {
					mpis[i] = mpis[p] * args[i];
				}
			}
		}
		int result = mpis[0];
		for (int i = 1; i < Array; i++) {
			if (mpis[i] > result) {
				result = mpis[i];
			}
		}
		return result;
	}
}
