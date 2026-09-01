import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractMax {

	public static int extractMax(String string) {
		String[] output = string.split("[^0-9]+");
		int Max = Integer.MIN_VALUE;
		for (String str : output) {
			if (str.length() > 0) {
				int i = Integer.parseInt(str);
				if (i > Max) {
					Max = i;
				}
			}
		}
		return Max;
	}
}
