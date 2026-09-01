
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivisionElements {

	public static int[] divisionElements(int[] division, int[] test) {
		int Array = division.length;
		int[] elements = new int[Array];
		for (int index = 0; index < Array; index++) {
			elements[index] = division[index]
					/ test[index];
		}
		return elements;
	}
}
