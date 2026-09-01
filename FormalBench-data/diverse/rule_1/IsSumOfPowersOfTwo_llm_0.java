import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSumOfPowersOfTwo {

	public static Boolean isSumOfPowersOfTwo(int num) {
		if (num == 1) {
			return true;
		}
		if (num == 0) {
			return false;
		}
		return (isSumOfPowersOfTwo(num - 1) || (num & (num - 1)) != 0)
				&& (num & 1) == 0;
	}
}
