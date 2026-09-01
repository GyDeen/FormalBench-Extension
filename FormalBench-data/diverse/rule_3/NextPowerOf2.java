import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NextPowerOf2 {

	public static int nextPowerOf2(int n) {
		if (n == 0) {
			return 1;
		}
		int i = 1;
		for (; i < n;) {
			i = i << 1;
		}
		return i;
	}
}
