import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFac {

	public static int countFac(int n) {
		int count = 0;
		if (n == 1)
			return 1;
		int result = 2;
		while (result <= n / result) {
			if (n % result == 0) {
				count++;
				if (n / result == result) {
					break;
				}
			}
			result++;
		}
		return count;
	}
}
