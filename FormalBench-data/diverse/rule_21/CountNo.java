import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNo {

	public static int countNo(int a, int n, int l, int r) {
		int count = 0;
		for (int i = l; i <= r; i++) {
			if (i % a != 0) {
				count++;
			}
			if (n == count) {
				return i;
			}
		}
		return -1;
	}
}
