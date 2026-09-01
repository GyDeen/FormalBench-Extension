
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleIntStr {

	public static int[][] tupleIntStr(String[][] string) {
		int[][] tuple = new int[string.length][];

		for (int num = 0; num < string.length; num++) {
			tuple[num] = new int[string[num].length];
			for (int c = 0; c < string[num].length; c++) {
				tuple[num][c] = Integer
						.parseInt(string[num][c]);
			}
		}

		return tuple;
	}
}
