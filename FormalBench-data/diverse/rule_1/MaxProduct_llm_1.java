
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
			for (int k = 0; k < i; k++) {
				if (args[i] > args[k]
						&& mpis[i] < (mpis[k] * args[i])) {
					mpis[i] = mpis[k] * args[i];
				}
			}
		}
		int num = mpis[0];
		for (int i = 1; i < Array; i++) {
			if (mpis[i] > num) {
				num = mpis[i];
			}
		}
		return num;
	}
}
