
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortSublists {

	public static String[][] sortSublists(String[][] arrays) {
		String[][] sortedArrays = new String[arrays.length][];

		int index = 0;
		while (index < arrays.length) {
			String[] sublist = arrays[index];
			Arrays.sort(sublist);
			sortedArrays[index] = sublist;
			index++;
		}

		return sortedArrays;
	}
}
