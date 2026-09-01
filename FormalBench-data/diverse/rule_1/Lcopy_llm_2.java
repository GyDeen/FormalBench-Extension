
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class Lcopy {

	public static int[] lcopy(int[] l) {
		int[] ret = new int[l.length];
		System.arraycopy(l, 0, ret, 0, l.length);
		return ret;
	}
}
