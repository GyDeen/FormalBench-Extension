import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDigit {

	public static int countDigit(int number) {
		int ret = 0;
		while (number > 0) {
			number /= 10;
			ret++;
		}
		return ret;
	}
}
