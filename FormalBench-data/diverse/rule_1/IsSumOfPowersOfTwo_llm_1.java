import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSumOfPowersOfTwo {

	public static Boolean isSumOfPowersOfTwo(int p) {
		if (p == 1) {
			return true;
		}
		if (p == 0) {
			return false;
		}
		return (isSumOfPowersOfTwo(p - 1) || (p & (p - 1)) != 0)
				&& (p & 1) == 0;
	}
}
