
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class Lcopy {

	public static int[] lcopy(int[] L) {
		int[] x = new int[L.length];
		System.arraycopy(L, 0, x, 0, L.length);
		return x;
	}
}
