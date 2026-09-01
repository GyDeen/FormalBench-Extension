
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivisionElements {

	public static int[] divisionElements(int[] input, int[] test) {
		int division = input.length;
		int[] elements = new int[division];
		for (int index = 0; index < division; index++) {
			elements[index] = input[index]
					/ test[index];
		}
		return elements;
	}
}
