import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFac {

	public static int countFac(int num) {
		int result = 0;
		if (num == 1)
			return 1;
		for (int c = 2; c <= num / c; c++) {
			if (num % c == 0) {
				result++;
				if (num / c == c) {
					break;
				}
			}
		}
		return result;
	}
}
