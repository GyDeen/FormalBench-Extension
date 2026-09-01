
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPosition {

	public static int getPosition(int[] a, int n, int m) {
		int pos = 0;
		int ret = 0;
		while (ret < a.length) {
			if (a[ret] == n) {
				pos = ret;
				break;
			}
			ret++;
		}
		return pos + m;
	}
}
