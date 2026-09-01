import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNo {

	public static int countNo(int code, int b, int l, int m) {
		int ret = 0;
		for (int c = l; c <= m; c++) {
			if (c % code != 0) {
				ret++;
			}
			if (ret == b) {
				return c;
			}
		}
		return -1;
	}
}
