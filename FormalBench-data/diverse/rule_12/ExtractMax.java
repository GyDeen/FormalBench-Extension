import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractMax {

	public static int extractMax(String input) {
		int max = Integer.MIN_VALUE;
		String[] split = input.split("[^0-9]+");
		for (String s : split) {
			if (s.length() > 0) {
				int num = Integer.parseInt(s);
				if (num > max) {
					max = num;
				}
			}
		}
		return max;
	}
}
