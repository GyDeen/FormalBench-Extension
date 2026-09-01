
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortSublists {

	public static String[][] sortSublists(String[][] array) {
		String[][] result = new String[array.length][];

		for (int index = 0; index < array.length; index++) {
			String[] tmp = array[index];
			Arrays.sort(tmp);
			result[index] = tmp;
		}

		return result;
	}
}
