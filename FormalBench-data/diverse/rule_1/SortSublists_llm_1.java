
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortSublists {

	public static String[][] sortSublists(String[][] Sort) {
		String[][] array = new String[Sort.length][];

		for (int index = 0; index < Sort.length; index++) {
			String[] sorted = Sort[index];
			Arrays.sort(sorted);
			array[index] = sorted;
		}

		return array;
	}
}
