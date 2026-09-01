
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Extract {

	public static int[] extract(int[][] array) {
		int[] arr = new int[array.length];
		for (int l = 0; l < array.length; l++) {
			arr[l] = array[l][0];
		}
		return arr;
	}
}
