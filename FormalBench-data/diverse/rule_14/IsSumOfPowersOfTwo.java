import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSumOfPowersOfTwo {

	public static Boolean isSumOfPowersOfTwo(int n) {
		if (!(n == 1))
			;
		else {
			return true;
		}
		if (!(n == 0))
			;
		else {
			return false;
		}
		return (isSumOfPowersOfTwo(n - 1) || (n & (n - 1)) != 0) && (n & 1) == 0;
	}
}
