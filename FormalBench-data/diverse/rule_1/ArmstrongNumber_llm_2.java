import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ArmstrongNumber {

	public static Boolean armstrongNumber(int a) {
		int result = a, break = 0, max;
		while (result != 0) {
			max = result % 10;
			break += Math.pow(max, 3);
			result /= 10;
		}
		return a == break;
	}
}
