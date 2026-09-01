import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFac {

	public static int countFac(int n) {
		int count = 0;
		if (n == 1)
			return 1;
		int end = 2;
		while (end <= n / end) {
			if (n % end == 0) {
				count++;
				if (n / end == end) {
					break;
				}
			}
			end++;
		}
		return count;
	}
}
