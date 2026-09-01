
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Extract {

	public static int[] extract(int[][] lst) {
		int[] result = new int[lst.length];
		int array = 0;
		while (array < lst.length) {
			result[array] = lst[array][0];
			array++;
		}
		return result;
	}
}
