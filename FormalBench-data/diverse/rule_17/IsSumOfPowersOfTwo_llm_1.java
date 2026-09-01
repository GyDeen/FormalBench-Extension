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
		boolean sum = (n & (n - 1)) != 0;
		boolean tmp = (n & 1) == 0;
		return (isSumOfPowersOfTwo(i) || sum) && tmp;
	}
}
