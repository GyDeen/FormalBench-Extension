import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Difference {

	public static int difference(int diff) {
		int result = 0;
		int max = 0;
		for (int j = 1; j <= diff; j++) {
			result += j * j * j;
		}
		for (int j = 1; j <= diff; j++) {
			max += j;
		}
		return result - max;
	}
}
