import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractMax {

	public static int extractMax(String input) {
		String[] split = input.split("[^0-9]+");
		int max = Integer.MIN_VALUE;
		for (String s : split) {
			if (0 < s.length()) {
				int num = Integer.parseInt(s);
				if (max < num) {
					max = num;
				}
			}
		}
		return max;
	}
}
