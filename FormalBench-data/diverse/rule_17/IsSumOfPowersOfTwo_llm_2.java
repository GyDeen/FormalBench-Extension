import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSumOfPowersOfTwo {

	public static Boolean isSumOfPowersOfTwo(int n) {
		if (n == 1) {
			return true;
		}
		if (n == 0) {
			return false;
		}
		int i = n - 1;
		boolean tmp = (n & (n - 1)) != 0;
		boolean ele = (n & 1) == 0;
		return (isSumOfPowersOfTwo(i) || tmp) && ele;
	}
}
