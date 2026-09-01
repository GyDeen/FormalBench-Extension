
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumPositivenum {

	public static int sumPositivenum(int[] arr) {
		int ret = 0;
		for (int i : arr) {
			if (i > 0) {
				ret += i;
			}
		}
		return ret;
	}
}
