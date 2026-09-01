import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNo {

	public static int countNo(int a, int n, int l, int r) {
		int count = 0;
		for (int i = l; r >= i; i++) {
			if (0 != i % a) {
				count++;
			}
			if (count == n) {
				return i;
			}
		}
		return -1;
	}
}
