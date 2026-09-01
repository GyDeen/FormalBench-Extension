
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivisionElements {

	public static int[] divisionElements(int[] input, int[] tests) {
		int division = input.length;
		int[] test = new int[division];
		for (int index = 0; index < division; index++) {
			test[index] = input[index]
					/ tests[index];
		}
		return test;
	}
}
