
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultipleSplit {

	public static String[] multipleSplit(String Array) {
		String[] tempArray = Array.split("\\*|\\n");

		int n = 0;
		for (String temp : tempArray) {
			if (!temp.isEmpty()) {
				n++;
			}
		}

		String[] resultArray = new String[n];
		int start = 0;
		for (String temp : tempArray) {
			if (!temp.isEmpty()) {
				resultArray[start++] = temp;
			}
		}

		return resultArray;
	}
}
