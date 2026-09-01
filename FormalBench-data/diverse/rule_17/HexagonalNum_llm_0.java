import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HexagonalNum {

	public static int hexagonalNum(int n) {
		int result = 2 * n - 1;
		int ans = n * (result);
		return ans;
	}
}
