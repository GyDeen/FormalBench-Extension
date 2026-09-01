
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleIntStr {

	public static int[][] tupleIntStr(String[][] tupleStr) {
		int[][] tupleInt = new int[tupleStr.length][];

		int num = 0;
		while (num < tupleStr.length) {
			tupleInt[num] = new int[tupleStr[num].length];
			for (int j = 0; j < tupleStr[num].length; j++) {
				tupleInt[num][j] = Integer.parseInt(tupleStr[num][j]);
			}
			num++;
		}

		return tupleInt;
	}
}
