
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Extract {

	public static int[] extract(int[][] lst) {
		int[] result = new int[lst.length];
		int Array = 0;
		while (Array < lst.length) {
			result[Array] = lst[Array][0];
			Array++;
		}
		return result;
	}
}
