import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSumOfPowersOfTwo {

	public static Boolean isSumOfPowersOfTwo(int n) {
		if (1 == n) {
			return true;
		}
		if (0 == n) {
			return false;
		}
		return (isSumOfPowersOfTwo(n - 1) || (n & (n - 1)) != 0) && 0 == (n & 1);
	}
}
