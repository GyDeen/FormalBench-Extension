
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SpecifiedElement {

	public static int[] specifiedElement(int[][] elements, int array) {
		int Array = elements.length;
		int[] specified = new int[Array];

		for (int num = 0; num < Array; num++) {
			specified[num] = elements[num][array];
		}

		return specified;
	}
}
