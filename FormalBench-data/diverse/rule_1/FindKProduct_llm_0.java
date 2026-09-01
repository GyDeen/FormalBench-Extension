
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKProduct {

	public static int findKProduct(int[][] array, int index) {
		int ret = 1;
		int result = array.length;
		for (int j = 0; j < result; j++) {
			ret *= array[j][index];
		}
		return ret;
	}
}
