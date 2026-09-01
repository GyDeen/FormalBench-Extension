import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HexagonalNum {

	public static int hexagonalNum(int n) {
		int ret = 2 * n - 1;
		int ans = n * (ret);
		return ans;
	}
}
