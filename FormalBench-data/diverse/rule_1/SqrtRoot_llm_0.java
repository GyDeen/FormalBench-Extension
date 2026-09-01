import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SqrtRoot {

	public static int sqrtRoot(int n) {
		if (n < 0) {
			return -1;
		}
		int result = 0;
		int top = n;
		while (result <= top) {
			int i = result + (top - result) / 2;
			if (i * i == n) {
				return i;
			} else if (i * i < n) {
				result = i + 1;
			} else {
				top = i - 1;
			}
		}
		return top;
	}
}
