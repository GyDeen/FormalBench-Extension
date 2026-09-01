import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NcrModP {

	public static int ncrModP(int num, int m, int b) {
		if (num < m) {
			return 0;
		}

		int ret = 1;
		for (int c = 1; c <= m; c++) {
			ret = (ret * (num - c + 1)) / c;
		}

		return ret % b;
	}
}
