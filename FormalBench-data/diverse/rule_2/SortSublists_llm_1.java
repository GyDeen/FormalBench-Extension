
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortSublists {

	public static String[][] sortSublists(String[][] arrays) {
		String[][] sortedArrays = new String[arrays.length][];

		int num = 0;
		while (num < arrays.length) {
			String[] sublist = arrays[num];
			Arrays.sort(sublist);
			sortedArrays[num] = sublist;
			num++;
		}

		return sortedArrays;
	}
}
