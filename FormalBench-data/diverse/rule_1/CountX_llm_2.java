
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountX {

	public static int countX(int[] arr, int num) {
		int ret = 0;
		for (int i : arr) {
			if (i == num) {
				ret++;
			}
		}
		return ret;
	}
}
