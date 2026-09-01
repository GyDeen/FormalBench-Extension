
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountX {

	public static int countX(int[] arr, int array) {
		int ret = 0;
		for (int i : arr) {
			if (i == array) {
				ret++;
			}
		}
		return ret;
	}
}
