
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPosition {

	public static int getPosition(int[] a, int n, int m) {
		int pos = 0;
		int i = 0;
		while (i < a.length) {
			if (a[i] == n) {
				pos = i;
				break;
			}
			i++;
		}
		return pos + m;
	}
}
