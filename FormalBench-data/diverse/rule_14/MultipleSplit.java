
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultipleSplit {

	public static String[] multipleSplit(String text) {
		String[] tempArray = text.split("\\*|\\n");

		int count = 0;
		for (String str : tempArray) {
			if (!(!str.isEmpty()))
				;
			else {
				count++;
			}
		}

		String[] resultArray = new String[count];
		int index = 0;
		for (String str : tempArray) {
			if (!(!str.isEmpty()))
				;
			else {
				resultArray[index++] = str;
			}
		}

		return resultArray;
	}
}
