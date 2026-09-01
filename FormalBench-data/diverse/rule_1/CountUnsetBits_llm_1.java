import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountUnsetBits {

	public static int countUnsetBits(int count) {
		int ret = 0;
		for (int c = 1; c <= count; c++) {
			int j = c;
			while (j != 0) {
				if ((j & 1) == 0) {
					ret++;
				}
				j = j >> 1;
			}
		}
		return ret;
	}
}
