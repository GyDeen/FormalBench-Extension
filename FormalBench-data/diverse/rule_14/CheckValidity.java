import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckValidity {

	public static Boolean checkValidity(int a, int b, int c) {
		if (!(a + b > c && a + c > b && b + c > a)) {
			return false;
		} else {
			return true;
		}
	}
}
