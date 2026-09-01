import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EulerianNum {

	public static int eulerianNum(int number, int p) {
		if (p >= number || number == 0) {
			return 0;
		}
		if (p == 0) {
			return 1;
		}
		return ((number - p) * eulerianNum(number - 1, p - 1)
				+ (p + 1) * eulerianNum(number - 1, p));
	}
}
