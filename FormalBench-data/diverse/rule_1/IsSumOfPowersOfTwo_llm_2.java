import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSumOfPowersOfTwo {

	public static Boolean isSumOfPowersOfTwo(int number) {
		if (number == 1) {
			return true;
		}
		if (number == 0) {
			return false;
		}
		return (isSumOfPowersOfTwo(number - 1) || (number & (number - 1)) != 0)
				&& (number & 1) == 0;
	}
}
