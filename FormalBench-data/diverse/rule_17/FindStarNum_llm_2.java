import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindStarNum {

	public static int findStarNum(int n) {
		int ret = 6 * n * (n - 1);
		return (ret + 1);
	}
}
