import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNo {

	public static int countNo(int code, int b, int l, int c) {
		int ret = 0;
		for (int loop = l; loop <= c; loop++) {
			if (loop % code != 0) {
				ret++;
			}
			if (ret == b) {
				return loop;
			}
		}
		return -1;
	}
}
