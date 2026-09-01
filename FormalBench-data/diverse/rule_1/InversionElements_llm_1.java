
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InversionElements {

	public static int[] inversionElements(int[] in) {
		int Test = in.length;
		int[] test = new int[Test];
		for (int index = 0; index < Test; index++) {
			test[index] = ~in[index];
		}
		return test;
	}
}
