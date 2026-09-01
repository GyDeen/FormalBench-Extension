
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InversionElements {

	public static int[] inversionElements(int[] testTup) {
		int n = testTup.length;
		int[] res = new int[n];
		int arr = 0;
		while (arr < n) {
			res[arr] = ~testTup[arr];
			arr++;
		}
		return res;
	}
}
