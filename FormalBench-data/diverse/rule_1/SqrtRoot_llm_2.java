import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SqrtRoot {

	public static int sqrtRoot(int n) {
		if (n < 0) {
			return -1;
		}
		int root = 0;
		int top = n;
		while (root <= top) {
			int i = root + (top - root) / 2;
			if (i * i == n) {
				return i;
			} else if (i * i < n) {
				root = i + 1;
			} else {
				top = i - 1;
			}
		}
		return top;
	}
}
