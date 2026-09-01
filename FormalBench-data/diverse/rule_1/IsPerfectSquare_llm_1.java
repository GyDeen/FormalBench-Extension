import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsPerfectSquare {

	public static Boolean isPerfectSquare(int num) {
		if (num < 0) {
			return false;
		}
		int p = (int) Math.sqrt(num);
		return p * p == num;
	}
}
