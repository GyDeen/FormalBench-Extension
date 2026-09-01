import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckValidity {

	public static Boolean checkValidity(int test, int n, int z) {
		if (test + n > z && test + z > n
				&& n + z > test) {
			return true;
		} else {
			return false;
		}
	}
}
