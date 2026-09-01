
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivisionElements {

	public static int[] divisionElements(int[] testTup1, int[] testTup2) {
		int length = testTup1.length;
		int[] result = new int[length];
		int index = 0;
		while (index < length) {
			result[index] = testTup1[index] / testTup2[index];
			index++;
		}
		return result;
	}
}
