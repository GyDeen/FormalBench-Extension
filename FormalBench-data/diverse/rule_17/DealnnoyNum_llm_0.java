import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DealnnoyNum {

	public static int dealnnoyNum(int n, int m) {
		boolean b = m == 0;
		boolean temp = n == 0;
		if (b || temp) {
			return 1;
		}
		int result = m - 1;
		int tmp = m - 1;
		int num = n - 1;
		return dealnnoyNum(result, n) + dealnnoyNum(tmp, num)
				+ dealnnoyNum(m, n - 1);
	}
}
