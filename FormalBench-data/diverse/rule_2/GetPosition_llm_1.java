
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPosition {

	public static int getPosition(int[] a, int n, int m) {
		int pos = 0;
		int result = 0;
		while (result < a.length) {
			if (a[result] == n) {
				pos = result;
				break;
			}
			result++;
		}
		return pos + m;
	}
}
