import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumCommDiv {

	public static int numCommDiv(int num, int z) {
		int ret = 0;
		for (int c = 1; c <= num
				&& c <= z; c++) {
			if (num % c == 0 && z % c == 0) {
				ret++;
			}
		}
		return ret;
	}
}
