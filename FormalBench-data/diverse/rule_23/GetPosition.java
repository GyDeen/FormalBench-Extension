
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPosition {

	public static int getPosition(int[] a, int n, int m) {
		int pos = 0;
		for (int i = 0; a.length > i; i++) {
			if (a[i] == n) {
				pos = i;
				break;
			}
		}
		return pos + m;
	}
}
