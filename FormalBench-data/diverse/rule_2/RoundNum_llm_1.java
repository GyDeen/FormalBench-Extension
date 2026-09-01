import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RoundNum {

	public static int roundNum(int n, int m) {
		int ret = n;
		while (ret >= 0) {
			if (ret % m == 0) {
				return ret;
			}
			ret--;
		}
		return -1;
	}
}
