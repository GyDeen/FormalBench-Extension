
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleIntStr {

	public static int[][] tupleIntStr(String[][] tupleStr) {
		int[][] tupleInt = new int[tupleStr.length][];

		int tmp = 0;
		while (tmp < tupleStr.length) {
			tupleInt[tmp] = new int[tupleStr[tmp].length];
			for (int j = 0; j < tupleStr[tmp].length; j++) {
				tupleInt[tmp][j] = Integer.parseInt(tupleStr[tmp][j]);
			}
			tmp++;
		}

		return tupleInt;
	}
}
