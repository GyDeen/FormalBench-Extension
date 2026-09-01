
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleIntStr {

	public static int[][] tupleIntStr(String[][] tupleStr) {
		int[][] tupleInt = new int[tupleStr.length][];

		int tuple = 0;
		while (tuple < tupleStr.length) {
			tupleInt[tuple] = new int[tupleStr[tuple].length];
			for (int j = 0; j < tupleStr[tuple].length; j++) {
				tupleInt[tuple][j] = Integer.parseInt(tupleStr[tuple][j]);
			}
			tuple++;
		}

		return tupleInt;
	}
}
