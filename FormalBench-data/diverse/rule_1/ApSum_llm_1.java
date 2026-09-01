import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ApSum {

	public static int apSum(int p, int b, int c) {
		int ret = 0;
		for (int count = 0; count < b; count++) {
			ret += (p + count * c);
		}
		return ret;
	}
}
