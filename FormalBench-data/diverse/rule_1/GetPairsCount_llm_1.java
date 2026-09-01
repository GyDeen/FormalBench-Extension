
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPairsCount {

	public static int getPairsCount(int[] array, int num, int p) {
		int ret = 0;
		for (int index = 0; index < num; index++) {
			for (int j = index + 1; j < num; j++) {
				if (array[index] + array[j] == p) {
					ret++;
				}
			}
		}
		return ret;
	}
}
