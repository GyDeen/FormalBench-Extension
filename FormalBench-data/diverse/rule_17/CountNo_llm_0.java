import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNo {

	public static int countNo(int a, int n, int l, int r) {
		int count = 0;
		for (int i = l; i <= r; i++) {
			int j = i % a;
			if (j != 0) {
				count++;
			}
			if (count == n) {
				return i;
			}
		}
		return -1;
	}
}
