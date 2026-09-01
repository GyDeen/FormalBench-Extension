import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLucas {

	public static int findLucas(int num) {
		int[] result = new int[num + 1];
		result[0] = 2;
		result[1] = 1;
		for (int l = 2; l <= num; l++) {
			result[l] = result[l - 1]
					+ result[l - 2];
		}
		return result[num];
	}
}
