import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractMax {

	public static int extractMax(String str) {
		String[] output = str.split("[^0-9]+");
		int Max = Integer.MIN_VALUE;
		for (String string : output) {
			if (string.length() > 0) {
				int i = Integer.parseInt(string);
				if (i > Max) {
					Max = i;
				}
			}
		}
		return Max;
	}
}
