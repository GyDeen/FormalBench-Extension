
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SpecifiedElement {

	public static int[] specifiedElement(int[][] array, int Array) {
		int num = array.length;
		int[] specified = new int[num];

		for (int index = 0; index < num; index++) {
			specified[index] = array[index][Array];
		}

		return specified;
	}
}
