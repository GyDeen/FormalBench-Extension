
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InversionElements {

	public static int[] inversionElements(int[] testTup) {
		int n = testTup.length;
		int[] res = new int[n];
		int temp = 0;
		while (temp < n) {
			res[temp] = ~testTup[temp];
			temp++;
		}
		return res;
	}
}
