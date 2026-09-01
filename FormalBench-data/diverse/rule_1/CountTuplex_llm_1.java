
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountTuplex {

	public static int countTuplex(int[] arr, int num) {
		int ret = 0;
		for (int index = 0; index < arr.length; index++) {
			if (arr[index] == num) {
				ret++;
			}
		}
		return ret;
	}
}
