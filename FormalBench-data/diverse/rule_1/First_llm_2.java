
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class First {

	public static int first(int[] args, int Array, int n) {
		int i = 0;
		int hi = n - 1;
		int result = -1;
		while (i <= hi) {
			int tmp = (i + hi) / 2;
			if (args[tmp] == Array) {
				result = tmp;
				hi = tmp - 1;
			} else if (args[tmp] < Array) {
				i = tmp + 1;
			} else {
				hi = tmp - 1;
			}
		}
		return result;
	}
}
