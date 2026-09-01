
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleIntStr {

	public static int[][] tupleIntStr(String[][] string) {
		int[][] tuple = new int[string.length][];

		for (int index = 0; index < string.length; index++) {
			tuple[index] = new int[string[index].length];
			for (int c = 0; c < string[index].length; c++) {
				tuple[index][c] = Integer
						.parseInt(string[index][c]);
			}
		}

		return tuple;
	}
}
