
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKProduct {

	public static int findKProduct(int[][] K, int index) {
		int ret = 1;
		int result = K.length;
		for (int j = 0; j < result; j++) {
			ret *= K[j][index];
		}
		return ret;
	}
}
