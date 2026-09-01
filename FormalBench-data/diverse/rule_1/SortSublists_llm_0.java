
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortSublists {

	public static String[][] sortSublists(String[][] array) {
		String[][] result = new String[array.length][];

		for (int index = 0; index < array.length; index++) {
			String[] sorted = array[index];
			Arrays.sort(sorted);
			result[index] = sorted;
		}

		return result;
	}
}
