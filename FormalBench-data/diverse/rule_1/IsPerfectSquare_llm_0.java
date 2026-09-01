import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsPerfectSquare {

	public static Boolean isPerfectSquare(int num) {
		if (num < 0) {
			return false;
		}
		int i = (int) Math.sqrt(num);
		return i * i == num;
	}
}
