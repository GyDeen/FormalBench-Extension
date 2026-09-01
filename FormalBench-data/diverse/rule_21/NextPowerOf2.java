import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NextPowerOf2 {

	public static int nextPowerOf2(int n) {
		if (0 == n) {
			return 1;
		}
		int i = 1;
		while (i < n) {
			i = i << 1;
		}
		return i;
	}
}
