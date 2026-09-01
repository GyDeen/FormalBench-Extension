import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNo {

	public static int countNo(int a, int n, int l, int r) {
		int count = 0;
		int ret = l;
		while (ret <= r) {
			if (ret % a != 0) {
				count++;
			}
			if (count == n) {
				return ret;
			}
			ret++;
		}
		return -1;
	}
}
