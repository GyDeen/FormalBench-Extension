import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckValidity {

	public static Boolean checkValidity(int a, int b, int c) {
		boolean check = a + b > c && a + c > b;
		boolean result = b + c > a;
		if (check && result) {
			return true;
		} else {
			return false;
		}
	}
}
