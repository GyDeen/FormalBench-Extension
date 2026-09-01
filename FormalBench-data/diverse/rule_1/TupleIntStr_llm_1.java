
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleIntStr {

	public static int[][] tupleIntStr(String[][] string) {
		int[][] tu = new int[string.length][];

		for (int tuple = 0; tuple < string.length; tuple++) {
			tu[tuple] = new int[string[tuple].length];
			for (int c = 0; c < string[tuple].length; c++) {
				tu[tuple][c] = Integer
						.parseInt(string[tuple][c]);
			}
		}

		return tu;
	}
}
