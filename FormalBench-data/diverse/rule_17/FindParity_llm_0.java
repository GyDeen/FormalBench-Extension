import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindParity {

	public static String findParity(int x) {
		int p = x % 2;
		if (p == 0) {
			return "Even Parity";
		} else {
			return "Odd Parity";
		}
	}
}
