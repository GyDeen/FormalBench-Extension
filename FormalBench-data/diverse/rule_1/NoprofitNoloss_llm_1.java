import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoprofitNoloss {

	public static Boolean noprofitNoloss(int n, int expected) {
		int actual = n - expected;
		int error = expected - n;
		if (actual == 0 && error == 0) {
			return true;
		}
		return false;
	}
}
