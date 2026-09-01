import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFac {

	public static int countFac(int n) {
		int count = 0;
		if (n == 1)
			return 1;
		int ret = 2;
		while (ret <= n / ret) {
			if (n % ret == 0) {
				count++;
				if (n / ret == ret) {
					break;
				}
			}
			ret++;
		}
		return count;
	}
}
