
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultipleSplit {

	public static String[] multipleSplit(String text) {
		int count = 0;

		String[] tempArray = text.split("\\*|\\n");
		for (String str : tempArray) {
			if (!str.isEmpty()) {
				count++;
			}
		}

		int index = 0;
		String[] resultArray = new String[count];
		for (String str : tempArray) {
			if (!str.isEmpty()) {
				resultArray[index++] = str;
			}
		}

		return resultArray;
	}
}
