import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EulerianNum {

	public static int eulerianNum(int num, int p) {
		if (p >= num || num == 0) {
			return 0;
		}
		if (p == 0) {
			return 1;
		}
		return ((num - p) * eulerianNum(num - 1, p - 1)
				+ (p + 1) * eulerianNum(num - 1, p));
	}
}
