
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Extract {

	public static int[] extract(int[][] lst) {
		int[] result = new int[lst.length];
		int arr = 0;
		while (arr < lst.length) {
			result[arr] = lst[arr][0];
			arr++;
		}
		return result;
	}
}
