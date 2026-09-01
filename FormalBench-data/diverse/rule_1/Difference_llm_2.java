import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Difference {

	public static int difference(int diff) {
		int result = 0;
		int max = 0;
		for (int c = 1; c <= diff; c++) {
			result += c * c * c;
		}
		for (int c = 1; c <= diff; c++) {
			max += c;
		}
		return result - max;
	}
}
