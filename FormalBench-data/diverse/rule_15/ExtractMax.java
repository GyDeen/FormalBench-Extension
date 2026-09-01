import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractMax {

	public static int extractMax(String input) {
		String[] split = input.split("[^0-9]+");
		int max = Integer.MIN_VALUE;
		for (String s : split) {
			if (s.length() > 0) {
				int num = Integer.parseInt(s);
				max = (num > max) ? num : max;
			}
		}
		return max;
	}
}
