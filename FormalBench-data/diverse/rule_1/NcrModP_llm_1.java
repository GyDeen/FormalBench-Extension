import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NcrModP {

	public static int ncrModP(int N, int k, int b) {
		if (N < k) {
			return 0;
		}

		int ret = 1;
		for (int c = 1; c <= k; c++) {
			ret = (ret * (N - c + 1)) / c;
		}

		return ret % b;
	}
}
