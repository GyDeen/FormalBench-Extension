
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortSublists {

	public static String[][] sortSublists(String[][] arrays) {
		String[][] sortedArrays = new String[arrays.length][];

		int size = 0;
		while (size < arrays.length) {
			String[] sublist = arrays[size];
			Arrays.sort(sublist);
			sortedArrays[size] = sublist;
			size++;
		}

		return sortedArrays;
	}
}
