
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Extract {

	public static int[] extract(int[][] array) {
		int[] split = new int[array.length];
		for (int index = 0; index < array.length; index++) {
			split[index] = array[index][0];
		}
		return split;
	}
}
