import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckValidity {

	public static Boolean checkValidity(int a, int b, int c) {
		if (c < a + b && b < a + c && a < b + c) {
			return true;
		} else {
			return false;
		}
	}
}
