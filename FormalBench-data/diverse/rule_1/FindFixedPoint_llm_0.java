
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFixedPoint {

	public static int findFixedPoint(int[] args, int array) {
		int ret = 0;
		int stop = array - 1;
		while (ret <= stop) {
			int i = ret + (stop - ret) / 2;
			if (args[i] == i) {
				return i;
			} else if (args[i] < i) {
				ret = i + 1;
			} else {
				stop = i - 1;
			}
		}
		return -1;
	}
}
