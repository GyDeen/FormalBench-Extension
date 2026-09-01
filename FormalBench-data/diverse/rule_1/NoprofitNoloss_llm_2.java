import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoprofitNoloss {

	public static Boolean noprofitNoloss(int num, int expected) {
		int actual = num - expected;
		int cost = expected - num;
		if (actual == 0 && cost == 0) {
			return true;
		}
		return false;
	}
}
