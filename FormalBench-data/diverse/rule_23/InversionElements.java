
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InversionElements {

	public static int[] inversionElements(int[] testTup) {
		int n = testTup.length;
		int[] res = new int[n];
		for (int i = 0; n > i; i++) {
			res[i] = ~testTup[i];
		}
		return res;
	}
}
