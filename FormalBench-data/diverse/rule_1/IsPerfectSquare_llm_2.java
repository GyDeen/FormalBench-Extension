import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsPerfectSquare {

	public static Boolean isPerfectSquare(int p) {
		if (p < 0) {
			return false;
		}
		int i = (int) Math.sqrt(p);
		return i * i == p;
	}
}
