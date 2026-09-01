
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPosition {

	public static int getPosition(int[] a, int n, int m) {
		int pos = 0;
		for (int i = 0; i < a.length; i++) {
			if (n == a[i]) {
				pos = i;
				break;
			}
		}
		return pos + m;
	}
}
