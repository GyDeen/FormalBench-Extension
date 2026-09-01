import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberCtr {

	public static int numberCtr(String str) {
		int count = 0;
		int max = 0;
		while (max < str.length()) {
			if (Character.isDigit(str.charAt(max))) {
				count++;
			}
			max++;
		}
		return count;
	}
}
