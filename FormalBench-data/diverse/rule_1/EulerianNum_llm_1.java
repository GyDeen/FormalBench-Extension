import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EulerianNum {

	public static int eulerianNum(int num, int k) {
		if (k >= num || num == 0) {
			return 0;
		}
		if (k == 0) {
			return 1;
		}
		return ((num - k) * eulerianNum(num - 1, k - 1)
				+ (k + 1) * eulerianNum(num - 1, k));
	}
}
