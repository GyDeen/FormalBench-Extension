
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class First {

	public static int first(int[] args, int array, int n) {
		int ret = 0;
		int hi = n - 1;
		int result = -1;
		while (ret <= hi) {
			int tmp = (ret + hi) / 2;
			if (args[tmp] == array) {
				result = tmp;
				hi = tmp - 1;
			} else if (args[tmp] < array) {
				ret = tmp + 1;
			} else {
				hi = tmp - 1;
			}
		}
		return result;
	}
}
