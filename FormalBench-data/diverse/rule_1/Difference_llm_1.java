import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Difference {

	public static int difference(int diff) {
		int ret = 0;
		int max = 0;
		for (int j = 1; j <= diff; j++) {
			ret += j * j * j;
		}
		for (int j = 1; j <= diff; j++) {
			max += j;
		}
		return ret - max;
	}
}
