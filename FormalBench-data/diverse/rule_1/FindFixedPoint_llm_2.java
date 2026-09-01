
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFixedPoint {

	public static int findFixedPoint(int[] i, int array) {
		int ret = 0;
		int stop = array - 1;
		while (ret <= stop) {
			int cur = ret + (stop - ret) / 2;
			if (i[cur] == cur) {
				return cur;
			} else if (i[cur] < cur) {
				ret = cur + 1;
			} else {
				stop = cur - 1;
			}
		}
		return -1;
	}
}
