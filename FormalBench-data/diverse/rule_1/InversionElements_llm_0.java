
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InversionElements {

	public static int[] inversionElements(int[] in) {
		int test = in.length;
		int[] temp = new int[test];
		for (int index = 0; index < test; index++) {
			temp[index] = ~in[index];
		}
		return temp;
	}
}
