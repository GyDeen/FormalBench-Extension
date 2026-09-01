
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPairsCount {

	public static int getPairsCount(int[] pairs, int num, int p) {
		int result = 0;
		for (int index = 0; index < num; index++) {
			for (int j = index + 1; j < num; j++) {
				if (pairs[index] + pairs[j] == p) {
					result++;
				}
			}
		}
		return result;
	}
}
