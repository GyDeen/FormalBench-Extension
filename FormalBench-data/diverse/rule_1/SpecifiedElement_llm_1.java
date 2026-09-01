
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SpecifiedElement {

	public static int[] specifiedElement(int[][] elements, int array) {
		int num = elements.length;
		int[] specified = new int[num];

		for (int index = 0; index < num; index++) {
			specified[index] = elements[index][array];
		}

		return specified;
	}
}
