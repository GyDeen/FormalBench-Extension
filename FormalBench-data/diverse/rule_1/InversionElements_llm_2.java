
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InversionElements {

	public static int[] inversionElements(int[] in) {
		int test = in.length;
		int[] result = new int[test];
		for (int index = 0; index < test; index++) {
			result[index] = ~in[index];
		}
		return result;
	}
}
